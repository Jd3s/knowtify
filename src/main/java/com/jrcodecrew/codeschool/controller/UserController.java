package com.jrcodecrew.codeschool.controller;

import com.jrcodecrew.codeschool.dto.ChildDto;
import com.jrcodecrew.codeschool.dto.LoginDto;
import com.jrcodecrew.codeschool.dto.UserDto;
import com.jrcodecrew.codeschool.model.Child;
import com.jrcodecrew.codeschool.model.User;
import com.jrcodecrew.codeschool.response.LoginResponse;
import com.jrcodecrew.codeschool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
