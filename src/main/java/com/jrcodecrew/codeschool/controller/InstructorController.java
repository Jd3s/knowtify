package com.jrcodecrew.codeschool.controller;

import com.jrcodecrew.codeschool.dto.InstructorDto;
import com.jrcodecrew.codeschool.dto.UserDto;
import com.jrcodecrew.codeschool.model.Instructor;
import com.jrcodecrew.codeschool.model.User;
import com.jrcodecrew.codeschool.service.InstructorService;
import com.jrcodecrew.codeschool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/instructor")
public class InstructorController {
  private InstructorService instructorService;

  @Autowired
  public InstructorController(InstructorService instructorService) {
    super();
    this.instructorService = instructorService;
  }

  @PostMapping("/add")
  public ResponseEntity<Instructor> saveUser(@RequestBody InstructorDto instructorDto) {
    Instructor instructor = instructorService.addInstructor(instructorDto);
    return new ResponseEntity<Instructor>(instructor, HttpStatus.CREATED);
  }
}
