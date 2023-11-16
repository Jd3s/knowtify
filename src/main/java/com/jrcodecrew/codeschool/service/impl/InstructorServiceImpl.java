package com.jrcodecrew.codeschool.service.impl;

import com.jrcodecrew.codeschool.dto.InstructorDto;
import com.jrcodecrew.codeschool.model.Instructor;
import com.jrcodecrew.codeschool.repository.InstructorRepository;
import com.jrcodecrew.codeschool.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorServiceImpl implements InstructorService {
  private InstructorRepository instructorRepository;

  @Autowired
  public InstructorServiceImpl(InstructorRepository instructorRepository) {
    super();
    this.instructorRepository = instructorRepository;
  }

  @Override
  public Instructor addInstructor(InstructorDto instructorDto) {
    Instructor instructor =
        new Instructor(
            instructorDto.getFirstName(),
            instructorDto.getLastName(),
            instructorDto.getEmail(),
            instructorDto.getPhone(),
            instructorDto.getYearsOfExperience(),
            instructorDto.getProficiency());
    return instructorRepository.save(instructor);
  }
}
