package com.jrcodecrew.codeschool.repository;

import com.jrcodecrew.codeschool.model.AgeGroup;
import com.jrcodecrew.codeschool.model.Child;
import com.jrcodecrew.codeschool.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

  Optional<Course> findByCourseId(String courseId);
  List<Course> findByAgeGroup(AgeGroup ageGroup);
}
