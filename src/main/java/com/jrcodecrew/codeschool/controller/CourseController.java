package com.jrcodecrew.codeschool.controller;

import com.jrcodecrew.codeschool.dto.ChildDto;
import com.jrcodecrew.codeschool.dto.CourseDto;
import com.jrcodecrew.codeschool.dto.LoginDto;
import com.jrcodecrew.codeschool.dto.UserDto;
import com.jrcodecrew.codeschool.model.Child;
import com.jrcodecrew.codeschool.model.Course;
import com.jrcodecrew.codeschool.model.User;
import com.jrcodecrew.codeschool.response.LoginResponse;
import com.jrcodecrew.codeschool.service.CourseService;
import com.jrcodecrew.codeschool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/course")
public class CourseController {
  private CourseService courseService;

  @Autowired
  public CourseController(CourseService courseService) {
    super();
    this.courseService = courseService;
  }

  @PostMapping("/addCourse")
  public ResponseEntity<Course> addCourse(@RequestBody CourseDto courseDto) {
    return new ResponseEntity<Course>(courseService.addCourse(courseDto), HttpStatus.CREATED);
  }

  @GetMapping("/getCourse/{courseId}")
  public ResponseEntity<Course> getCourseById(@PathVariable String courseId) {
    Course course = courseService.getCourseById(courseId);
    return ResponseEntity.ok(course);
  }

  @GetMapping("/getCourses")
  public ResponseEntity<List<Course>> getCoursesByAgeGroup(@RequestParam String ageGroup) {
    List<Course> course = courseService.getCoursesByAgeGroup(ageGroup);
    return ResponseEntity.ok(course);
  }

  @PostMapping("/addInstructorToCourse/{courseId}")
  public ResponseEntity<Course> addInstructorToCourse(@PathVariable String courseId, @RequestBody Map<String, Long> requestBody) {
    Course course = courseService.addInstructorToCourse(courseId, requestBody.get("instrcutorId"));
    return ResponseEntity.ok(course);
  }

}
