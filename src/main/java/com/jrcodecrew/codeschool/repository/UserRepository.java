package com.jrcodecrew.codeschool.repository;

import com.jrcodecrew.codeschool.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
