package com.jrcodecrew.codeschool.service;

import com.jrcodecrew.codeschool.dto.ChildDto;
import com.jrcodecrew.codeschool.dto.LoginDto;
import com.jrcodecrew.codeschool.dto.UserDto;
import com.jrcodecrew.codeschool.model.Child;
import com.jrcodecrew.codeschool.model.User;
import com.jrcodecrew.codeschool.response.LoginResponse;

import java.util.List;

public interface UserService {
  User saveUser(UserDto userDto);

  LoginResponse loginUser(LoginDto loginDto);

  User addChild(ChildDto childDtoDto);

  List<Child> getChildren(Long parentId);

  User getUser(String email);
}
