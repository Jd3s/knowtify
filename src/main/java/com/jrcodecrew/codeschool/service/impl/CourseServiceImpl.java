package com.jrcodecrew.codeschool.service.impl;

import com.jrcodecrew.codeschool.dto.CourseDto;
import com.jrcodecrew.codeschool.model.AgeGroup;
import com.jrcodecrew.codeschool.model.Course;
import com.jrcodecrew.codeschool.repository.CourseRepository;
import com.jrcodecrew.codeschool.repository.InstructorRepository;
import com.jrcodecrew.codeschool.service.CourseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
  private CourseRepository courseRepository;
  private InstructorRepository instructorRepository;

  @Autowired
  public CourseServiceImpl(
      CourseRepository courseRepository, InstructorRepository instructorRepository) {
    super();
    this.courseRepository = courseRepository;
    this.instructorRepository = instructorRepository;
  }

  @Override
  public Course addCourse(CourseDto courseDto) {
    Course addedCourse =
        new Course(
            courseDto.getCourseId(),
            courseDto.getCourseName(),
            courseDto.getDescription(),
            courseDto.getAgeGroup());
    return courseRepository.save(addedCourse);
  }

  @Override
  public Course getCourseById(String courseId) {
    return courseRepository
        .findByCourseId(courseId)
        .orElseThrow(
            () ->
                new EntityNotFoundException(
                    "Course with Id : " + courseId.toString() + " not found"));
  }

  @Override
  public List<Course> getCoursesByAgeGroup(String ageGroup) {
    AgeGroup courseAgeGroup;
    try {
      courseAgeGroup = Enum.valueOf(AgeGroup.class, ageGroup);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid Age group specified: " + ageGroup);
    }
    return courseRepository.findByAgeGroup(courseAgeGroup);
  }

  @Override
  public Course addInstructorToCourse(String courseId, Long instructorId) {
    Course course =
        courseRepository
            .findByCourseId(courseId)
            .orElseThrow(
                () -> new EntityNotFoundException("Course with id not found : " + courseId));
    course
        .getInstructors()
        .add(
            instructorRepository
                .findById(instructorId)
                .orElseThrow(
                    () ->
                        new EntityNotFoundException(
                            "Instrcutor with id not found : " + instructorId)));
    return courseRepository.save(course);
  }
}
