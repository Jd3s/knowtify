package com.jrcodecrew.codeschool.repository;

import com.jrcodecrew.codeschool.model.Course;
import com.jrcodecrew.codeschool.model.Enrollment;
import com.jrcodecrew.codeschool.model.EnrollmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
  List<Enrollment> findByChildIdAndStatus(Long childId, EnrollmentStatus status);

  @Query("SELECT e.course FROM Enrollment e WHERE e.child.id = :childId")
  List<Course> findCoursesByChildId(Long childId);
}
