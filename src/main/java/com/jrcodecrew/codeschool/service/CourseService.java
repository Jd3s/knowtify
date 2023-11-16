package com.jrcodecrew.codeschool.service;

import com.jrcodecrew.codeschool.dto.CourseDto;
import com.jrcodecrew.codeschool.model.Course;

import java.util.List;

public interface CourseService {
  Course addCourse(CourseDto courseDto);

  Course getCourseById(String courseId);

  List<Course> getCoursesByAgeGroup(String ageGroup);

  Course addInstructorToCourse(String courseId, Long instructorId);
}
