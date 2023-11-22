package com.jrcodecrew.codeschool.service.impl;

import com.jrcodecrew.codeschool.dto.InstructorDto;
import com.jrcodecrew.codeschool.dto.ScheduleDto;
import com.jrcodecrew.codeschool.exception.MultipleActiveEnrollmentsException;
import com.jrcodecrew.codeschool.model.*;
import com.jrcodecrew.codeschool.repository.InstructorRepository;
import com.jrcodecrew.codeschool.repository.ScheduleRepository;
import com.jrcodecrew.codeschool.service.InstructorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class InstructorServiceImpl implements InstructorService {
  private InstructorRepository instructorRepository;

  private ScheduleRepository scheduleRepository;

  public static boolean isOverlapping(LocalTime start1, LocalTime end1, LocalTime start2, LocalTime end2) {
    return start1.isBefore(end2) && start2.isBefore(end1);
  }

  @Autowired
  public InstructorServiceImpl(InstructorRepository instructorRepository, ScheduleRepository scheduleRepository) {
    super();
    this.instructorRepository = instructorRepository;
    this.scheduleRepository = scheduleRepository;
  }

  @Override
  public Instructor addInstructor(InstructorDto instructorDto) {
    Instructor instructor =
        new Instructor(
            instructorDto.getFirstName(),
            instructorDto.getLastName(),
            instructorDto.getEmail(),
            instructorDto.getPhone(),
            instructorDto.getYearsOfExperience(),
            instructorDto.getProficiency(),
            instructorDto.getMeetingLink());
    return instructorRepository.save(instructor);
  }

  @Override
  public Schedule addScheduleToInstructor(ScheduleDto scheduleDto) {
    List<Schedule> schedulesForTheDay =
            scheduleRepository.findByInstructorIdAndDay(
                    scheduleDto.getInstructorId(), scheduleDto.getDay());

    if (schedulesForTheDay.size() > 0) {
      for(Schedule s: schedulesForTheDay) {
           if(isOverlapping(s.getStartTime(),s.getEndTime(),scheduleDto.getStartTime(),scheduleDto.getEndTime()))
              throw new MultipleActiveEnrollmentsException();
      }
    }

    Instructor instructor =
            instructorRepository
                    .findById(scheduleDto.getInstructorId())
                    .orElseThrow(
                            () ->
                                    new EntityNotFoundException(
                                            "Instructor with id not found : " + scheduleDto.getInstructorId()));


    Schedule schedule = new Schedule( instructor, scheduleDto.getStartTime(), scheduleDto.getEndTime(), scheduleDto.getDay(), scheduleDto.getCap(), scheduleDto.getCurrently_enrolled());
    scheduleRepository.save(schedule);

    schedule.setStatus(EnrollmentStatus.ACTIVE);
    return schedule;

  }

  @Override
  public List<Schedule> getScheduleByInstructorId(Long instructorId) {
    return scheduleRepository.findByInstructorId(instructorId);
  }

  @Override
  public Boolean deleteSchedule(Long scheduleId) {
    Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new EntityNotFoundException("Schedule not found : " + scheduleId));;
    if(schedule.getCurrently_enrolled() == 0) {
      scheduleRepository.delete(schedule);
      return true;
    }

    return false;
  }

}
