package com.jrcodecrew.codeschool.repository;

import com.jrcodecrew.codeschool.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByInstructorId(Long instructorId);

    @Query("SELECT s FROM Schedule s WHERE s.instructor.id = :instructorId and s.day = :day")
    List<Schedule> findByInstructorIdAndDay(Long instructorId, int day);

}
