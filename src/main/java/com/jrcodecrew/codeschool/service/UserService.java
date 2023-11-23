package com.jrcodecrew.codeschool.service;

import com.jrcodecrew.codeschool.dto.*;
import com.jrcodecrew.codeschool.model.Child;
import com.jrcodecrew.codeschool.model.User;
import com.jrcodecrew.codeschool.response.LoginResponse;
import com.jrcodecrew.codeschool.response.UpdatedUserResponse;

import javax.mail.MessagingException;
import java.util.List;

public interface UserService {
  User saveUser(UserDto userDto);

  LoginResponse loginUser(LoginDto loginDto);

  User addChild(ChildDto childDtoDto);

  List<Child> getChildren(Long parentId);

  User getUser(String email);

  UpdatedUserResponse updateProfile(Long userId, UpdateUserDto updateUserDto);

  void sendEmail(Long userId ) throws MessagingException;


}
