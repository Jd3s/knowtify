package com.jrcodecrew.codeschool.service.impl;

import com.jrcodecrew.codeschool.dto.EnrollmentDto;
import com.jrcodecrew.codeschool.exception.MultipleActiveEnrollmentsException;
import com.jrcodecrew.codeschool.model.*;
import com.jrcodecrew.codeschool.repository.*;
import com.jrcodecrew.codeschool.service.EnrollmentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
  private EnrollmentRepository enrollmentRepository;
  private ChildRepository childRepository;
  private CourseRepository courseRepository;
  private InstructorRepository instructorRepository;
  private ScheduleRepository scheduleRepository;

  @Autowired
  public EnrollmentServiceImpl(
      EnrollmentRepository enrollmentRepository,
      ChildRepository childRepository,
      CourseRepository courseRepository,
      InstructorRepository instructorRepository, ScheduleRepository scheduleRepository) {
    super();
    this.enrollmentRepository = enrollmentRepository;
    this.childRepository = childRepository;
    this.courseRepository = courseRepository;
    this.instructorRepository = instructorRepository;
    this.scheduleRepository = scheduleRepository;
  }

  @Override
  public EnrollmentDto enrollChild(EnrollmentDto enrollmentDto, Long scheduleId) {
    List<Enrollment> activeEnrollment =
        enrollmentRepository.findByChildIdAndStatus(
            enrollmentDto.getChildId(), EnrollmentStatus.ACTIVE);
    if (activeEnrollment.size() > 0) {
      throw new MultipleActiveEnrollmentsException();
    }
    Child child =
        childRepository
            .findById(enrollmentDto.getChildId())
            .orElseThrow(
                () ->
                    new EntityNotFoundException(
                        "Child with id not found : " + enrollmentDto.getChildId()));
    Course course =
        courseRepository
            .findByCourseId(enrollmentDto.getCourseId())
            .orElseThrow(
                () ->
                    new EntityNotFoundException(
                        "Course with id not found : " + enrollmentDto.getCourseId()));
    Instructor instructor =
        instructorRepository
            .findById(enrollmentDto.getInstructorId())
            .orElseThrow(
                () ->
                    new EntityNotFoundException(
                        "Instructor with id not found : " + enrollmentDto.getInstructorId()));


    Schedule schedule =
            scheduleRepository
                    .findById(scheduleId)
                    .orElseThrow(
                            () ->
                                    new EntityNotFoundException(
                                            "Schedule with id not found : " + scheduleId));




   schedule.setCurrently_enrolled(schedule.getCurrently_enrolled()+1);
   scheduleRepository.save(schedule);



    Enrollment enrollment = new Enrollment(child, course, instructor, schedule);
    enrollmentRepository.save(enrollment);


    return enrollmentDto.setStatus(EnrollmentStatus.PENDING);
  }

  @Override
  public List<Course> getEnrolledCourses(Long childId) {
    return enrollmentRepository.findCoursesByChildId(childId);
  }

  @Override
  public Enrollment addScheduleToEnrollment(Long scheduleId, Long enrollmentId) {

    Schedule schedule =
            scheduleRepository
                    .findById(scheduleId)
                    .orElseThrow(
                            () ->
                                    new EntityNotFoundException(
                                            "Schedule with id not found : " + scheduleId));


    Enrollment enrollment= enrollmentRepository.findById(enrollmentId).orElseThrow(
            () ->
                    new EntityNotFoundException(
                            "Enrollment with id not found : " + enrollmentId));


    if (checkChildScheduleOverlap(enrollment.getChild(), schedule))
      throw new MultipleActiveEnrollmentsException();

    enrollment.getSchedule().add(schedule);
    return enrollmentRepository.save(enrollment);
  }

  @Override
  public Boolean checkEnroll(EnrollmentDto enrollmentDto, Long scheduleId) {

    List<Enrollment> activeEnrollment =
            enrollmentRepository.findByChildIdAndStatus(
                    enrollmentDto.getChildId(), EnrollmentStatus.ACTIVE);

    if (!activeEnrollment.isEmpty()) {
      throw new MultipleActiveEnrollmentsException();
    }


    Child child =
            childRepository
                    .findById(enrollmentDto.getChildId())
                    .orElseThrow(
                            () ->
                                    new EntityNotFoundException(
                                            "Child with id not found : " + enrollmentDto.getChildId()));
    Course course =
            courseRepository
                    .findByCourseId(enrollmentDto.getCourseId())
                    .orElseThrow(
                            () ->
                                    new EntityNotFoundException(
                                            "Course with id not found : " + enrollmentDto.getCourseId()));
    Instructor instructor =
            instructorRepository
                    .findById(enrollmentDto.getInstructorId())
                    .orElseThrow(
                            () ->
                                    new EntityNotFoundException(
                                            "Instructor with id not found : " + enrollmentDto.getInstructorId()));


    Schedule schedule =
            scheduleRepository
                    .findById(scheduleId)
                    .orElseThrow(
                            () ->
                                    new EntityNotFoundException(
                                            "Schedule with id not found : " + scheduleId));


    if(!(schedule.getCap() > schedule.getCurrently_enrolled()))
      throw new EntityNotFoundException(
              "Enrollment limit exceeded for : " + scheduleId);

    if (checkChildScheduleOverlap(child, schedule))
      throw new MultipleActiveEnrollmentsException();


    return true;
  }

  @Override
  public Boolean acceptEnroll(Long enrollmentId) {
    Enrollment enrollment = enrollmentRepository.findById(enrollmentId).orElseThrow(
            () ->
                    new EntityNotFoundException(
                            "Enrollment with id not found : " + enrollmentId));


    enrollment.setStatus(EnrollmentStatus.ACTIVE);
    return true;
  }

  @Override
  public List<Enrollment> getPendingEnrollments() {
    List<Enrollment> enrollments = enrollmentRepository.findAllByStatus(EnrollmentStatus.PENDING);
    return enrollments;
  }


  public static boolean isOverlapping(LocalTime start1, LocalTime end1, LocalTime start2, LocalTime end2) {
    return start1.isBefore(end2) && start2.isBefore(end1);
  }

  private Boolean checkChildScheduleOverlap(Child child, Schedule schedule) {
    // for each enrolled course, get all the schedules and check if each if it overlaps with the new schedule to be added.
   List<Enrollment> enrollments =  enrollmentRepository.findByChildIdAndStatus(child.getId(), EnrollmentStatus.ACTIVE);
   for(Enrollment enrollment : enrollments) {
     Set<Schedule> existingSchedules = enrollment.getSchedule();
     for(Schedule existingSchedule : existingSchedules){
       if (existingSchedule.getDay()==schedule.getDay()){
      if(isOverlapping(existingSchedule.getStartTime(), existingSchedule.getEndTime(), schedule.getStartTime(),schedule.getEndTime()))
        return true;
     }
       }
   }
   return false;
  }


}
