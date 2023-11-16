package com.jrcodecrew.codeschool.repository;

import com.jrcodecrew.codeschool.model.Instructor;
import com.jrcodecrew.codeschool.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {}
