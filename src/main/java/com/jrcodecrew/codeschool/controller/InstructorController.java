package com.jrcodecrew.codeschool.controller;

import com.jrcodecrew.codeschool.dto.InstructorDto;
import com.jrcodecrew.codeschool.dto.ScheduleDto;
import com.jrcodecrew.codeschool.model.Instructor;
import com.jrcodecrew.codeschool.model.Schedule;
import com.jrcodecrew.codeschool.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructor")
public class InstructorController {
  private InstructorService instructorService;

  @Autowired
  public InstructorController(InstructorService instructorService) {
    super();
    this.instructorService = instructorService;
  }

  @PostMapping("/add")
  public ResponseEntity<Instructor> saveUser(@RequestBody InstructorDto instructorDto) {
    Instructor instructor = instructorService.addInstructor(instructorDto);
    return new ResponseEntity<Instructor>(instructor, HttpStatus.CREATED);
  }

  @PostMapping("/addScheduleToInstructor/{instructorId}")
  public ResponseEntity<Schedule> addScheduleToInstructor(@RequestBody ScheduleDto scheduleDto) {
    Schedule schedule = instructorService.addScheduleToInstructor(scheduleDto);
    return ResponseEntity.ok(schedule);
  }

  @GetMapping("/getSchedule/{instructorId}")
  public ResponseEntity<List<Schedule>> getScheduleByInstructorId(@PathVariable Long instructorId) {
    List<Schedule> schedule = instructorService.getScheduleByInstructorId(instructorId);
    return ResponseEntity.ok(schedule);
  }

}
