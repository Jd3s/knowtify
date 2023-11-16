package com.jrcodecrew.codeschool.service;

import com.jrcodecrew.codeschool.dto.CourseDto;
import com.jrcodecrew.codeschool.dto.InstructorDto;
import com.jrcodecrew.codeschool.model.Course;
import com.jrcodecrew.codeschool.model.Instructor;

public interface InstructorService {
  Instructor addInstructor(InstructorDto instructorDto);
}
