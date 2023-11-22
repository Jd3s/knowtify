package com.jrcodecrew.codeschool.service;

import com.jrcodecrew.codeschool.dto.InstructorDto;
import com.jrcodecrew.codeschool.dto.ScheduleDto;
import com.jrcodecrew.codeschool.model.Instructor;
import com.jrcodecrew.codeschool.model.Schedule;

import java.util.List;

public interface InstructorService {
  Instructor addInstructor(InstructorDto instructorDto);

  Schedule addScheduleToInstructor(ScheduleDto scheduleDto);

  List<Schedule> getScheduleByInstructorId(Long instructorId);

  Boolean deleteSchedule(Long scheduleId);
}
