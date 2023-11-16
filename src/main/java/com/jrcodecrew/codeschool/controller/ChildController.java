package com.jrcodecrew.codeschool.controller;

import com.jrcodecrew.codeschool.dto.CourseDto;
import com.jrcodecrew.codeschool.dto.EnrollmentDto;
import com.jrcodecrew.codeschool.model.Course;
import com.jrcodecrew.codeschool.model.Enrollment;
import com.jrcodecrew.codeschool.service.CourseService;
import com.jrcodecrew.codeschool.service.EnrollmentService;
import jakarta.websocket.server.PathParam;
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

  @PostMapping("/enroll")
  public ResponseEntity<EnrollmentDto> enrollChild(@RequestBody EnrollmentDto enrollmentDto) {
    return new ResponseEntity<EnrollmentDto>(
        enrollmentService.enrollChild(enrollmentDto), HttpStatus.CREATED);
  }

  @GetMapping("/getEnrolledCourses/{childId}")
  public ResponseEntity<List<Course>> getActiveEnrollments(@PathVariable Long childId) {
    return ResponseEntity.ok(enrollmentService.getEnrolledCourses(childId));
  }
}
