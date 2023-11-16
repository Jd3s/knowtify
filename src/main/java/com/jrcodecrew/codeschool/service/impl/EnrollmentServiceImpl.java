package com.jrcodecrew.codeschool.service.impl;

import com.jrcodecrew.codeschool.dto.EnrollmentDto;
import com.jrcodecrew.codeschool.exception.MultipleActiveEnrollmentsException;
import com.jrcodecrew.codeschool.model.*;
import com.jrcodecrew.codeschool.repository.ChildRepository;
import com.jrcodecrew.codeschool.repository.CourseRepository;
import com.jrcodecrew.codeschool.repository.EnrollmentRepository;
import com.jrcodecrew.codeschool.repository.InstructorRepository;
import com.jrcodecrew.codeschool.service.EnrollmentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
  private EnrollmentRepository enrollmentRepository;
  private ChildRepository childRepository;
  private CourseRepository courseRepository;
  private InstructorRepository instructorRepository;

  @Autowired
  public EnrollmentServiceImpl(
      EnrollmentRepository enrollmentRepository,
      ChildRepository childRepository,
      CourseRepository courseRepository,
      InstructorRepository instructorRepository) {
    super();
    this.enrollmentRepository = enrollmentRepository;
    this.childRepository = childRepository;
    this.courseRepository = courseRepository;
    this.instructorRepository = instructorRepository;
  }

  @Override
  public EnrollmentDto enrollChild(EnrollmentDto enrollmentDto) {
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
    Enrollment enrollment = new Enrollment(child, course, instructor);
    enrollmentRepository.save(enrollment);
    return enrollmentDto.setStatus(EnrollmentStatus.ACTIVE);
  }

  @Override
  public List<Course> getEnrolledCourses(Long childId) {
    return enrollmentRepository.findCoursesByChildId(childId);
  }
}
