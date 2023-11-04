package com.jrcodecrew.codeschool.repository;

import com.jrcodecrew.codeschool.model.Child;
import com.jrcodecrew.codeschool.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChildRepository extends JpaRepository<Child, Long> {
  List<Child> findByUserParentId(Long parentId);
}
