package com.jrcodecrew.codeschool.controller;

import com.jrcodecrew.codeschool.dto.*;
import com.jrcodecrew.codeschool.model.Child;
import com.jrcodecrew.codeschool.model.User;
import com.jrcodecrew.codeschool.response.LoginResponse;
import com.jrcodecrew.codeschool.response.UpdatedUserResponse;
import com.jrcodecrew.codeschool.service.UserService;
import com.jrcodecrew.codeschool.service.util.EmailClient;
import org.hibernate.usertype.UserTypeSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
  private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    super();
    this.userService = userService;
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginDto loginDto) {
    LoginResponse loginResponse = userService.loginUser(loginDto);
    return ResponseEntity.ok(loginResponse);
  }

  @PostMapping("/register")
  public ResponseEntity<User> saveUser(@RequestBody UserDto userDto) {
    userDto.setRole(String.valueOf(User.Role.PARENT));
    User registeredUser = userService.saveUser(userDto);
    return new ResponseEntity<User>(registeredUser, HttpStatus.CREATED);
  }

  @GetMapping("/getUserByEmail/{email}")
  public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
    User user = userService.getUser(email);
    return ResponseEntity.ok(user);
  }

  @PostMapping("/addChild")
  public ResponseEntity<User> addChild(@RequestBody ChildDto childDto) {
    User registeredUser = userService.addChild(childDto);
    return new ResponseEntity<User>(registeredUser, HttpStatus.CREATED);
  }

  @GetMapping("/getChildren/{parentId}")
  public ResponseEntity<List<Child>> getChildren(@PathVariable Long parentId) {
    List<Child> children = userService.getChildren(parentId);
    return ResponseEntity.ok(children);
  }

  @PutMapping("/edit/{userId}")
  public ResponseEntity<UpdatedUserResponse> updateProfile(
      @PathVariable Long userId, @RequestBody UpdateUserDto updateUserDto) {
    UpdatedUserResponse updateProfile = userService.updateProfile(userId, updateUserDto);
    return ResponseEntity.ok(updateProfile);
  }


  @PostMapping("/sendEmail/{enrollmentId}")
  public ResponseEntity<String> sendEmail(
          @PathVariable Long enrollmentId) {
    try{
      userService.sendEmail(enrollmentId);
    }
    catch(MessagingException e){
      e.printStackTrace();

    }

    return ResponseEntity.ok("Email Sent successfully!");
  }

}
