package com.jrcodecrew.codeschool.controller;

import com.jrcodecrew.codeschool.dto.EnrollmentDto;
import com.jrcodecrew.codeschool.model.Course;
import com.jrcodecrew.codeschool.model.Enrollment;
import com.jrcodecrew.codeschool.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/child")
public class ChildController {
  private EnrollmentService enrollmentService;

  @Autowired
  public ChildController(EnrollmentService enrollmentService) {
    super();
    this.enrollmentService = enrollmentService;
  }

  @PostMapping("/checkEnroll")
  public Boolean checkEnroll(@RequestBody EnrollmentDto enrollmentDto) {
    return enrollmentService.checkEnroll(enrollmentDto);
  }


  @PostMapping("/acceptEnroll/{enrollmentId}")
  public Boolean acceptEnroll(@PathVariable Long enrollmentId) {
    return enrollmentService.acceptEnroll(enrollmentId);
  }


  @GetMapping("/getPendingEnrollments/")
  public ResponseEntity<List<Enrollment>> getPendingEnrollments() {
    return ResponseEntity.ok(enrollmentService.getPendingEnrollments());
  }

  @GetMapping("/getEnrolledCourses/{childId}")
  public ResponseEntity<List<Course>> getActiveEnrollments(@PathVariable Long childId) {
    return ResponseEntity.ok(enrollmentService.getEnrolledCourses(childId));
  }

  @PostMapping("/addScheduleToEnrollment/{enrollmentId}")
  public ResponseEntity<Enrollment> addScheduleToEnrollment(@RequestBody Long scheduleId, @PathVariable Long enrollmentId) {
    Enrollment enrollment = enrollmentService.addScheduleToEnrollment(scheduleId, enrollmentId);
    return ResponseEntity.ok(enrollment);
  }


}
