package com.jrcodecrew.codeschool.service;

import com.jrcodecrew.codeschool.dto.CourseDto;
import com.jrcodecrew.codeschool.model.Course;
import com.jrcodecrew.codeschool.model.Instructor;

import java.util.List;
import java.util.Set;

public interface CourseService {
  Course addCourse(CourseDto courseDto);

  Course getCourseById(String courseId);

  List<Course> getCoursesByAgeGroup(String ageGroup);

  Course addInstructorToCourse(String courseId, Long instructorId);

  Set<Instructor> getAllInstructorsForCourse(String courseId);
}
