package com.jrcodecrew.codeschool.repository;

import com.jrcodecrew.codeschool.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findOneByEmailAndPassword(String email, String password);

  Optional<User> findByEmail(String email);
}
