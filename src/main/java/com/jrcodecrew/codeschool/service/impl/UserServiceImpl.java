package com.jrcodecrew.codeschool.service.impl;

import com.jrcodecrew.codeschool.dto.ChildDto;
import com.jrcodecrew.codeschool.dto.LoginDto;
import com.jrcodecrew.codeschool.dto.UserDto;
import com.jrcodecrew.codeschool.exception.PhoneNumberException;
import com.jrcodecrew.codeschool.model.Child;
import com.jrcodecrew.codeschool.model.User;
import com.jrcodecrew.codeschool.repository.ChildRepository;
import com.jrcodecrew.codeschool.repository.UserRepository;
import com.jrcodecrew.codeschool.response.LoginResponse;
import com.jrcodecrew.codeschool.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;
  private ChildRepository childRepository;
  @Autowired private PasswordEncoder passwordEncoder;

  @Autowired
  public UserServiceImpl(UserRepository userRepository, ChildRepository childRepository) {
    super();
    this.userRepository = userRepository;
    this.childRepository = childRepository;
  }

  @Override
  public LoginResponse loginUser(LoginDto loginDto) {

    User user =
            userRepository
                .findByEmail(loginDto.getEmail())
                .orElseThrow(
                    () ->
                        new EntityNotFoundException(
                            "User not found with email: " + loginDto.getEmail()));
    if (user != null) {
      String password = loginDto.getPassword();
      String encodedPassword = user.getPassword();
      Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
      if (isPwdRight) {
        Optional<User> employee =
            userRepository.findOneByEmailAndPassword(loginDto.getEmail(), encodedPassword);
        if (employee.isPresent()) {
          return new LoginResponse("Login Success", true);
        } else {
          return new LoginResponse("Login Failed", false);
        }
      } else {
        return new LoginResponse("password Not Match", false);
      }
    } else {
      return new LoginResponse("Email not exists", false);
    }
  }

  @Override
  public User saveUser(UserDto userDto) {
    User.Role role;
    try {
      role = User.Role.valueOf(userDto.getRole().toUpperCase());
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid role specified: " + userDto.getRole());
    }
    if (role == User.Role.PARENT && userDto.getPhone() == null) {
      throw new PhoneNumberException();
    }
    User user =
        new User(
            userDto.getUserName(),
            this.passwordEncoder.encode(userDto.getPassword()),
            userDto.getEmail(),
            userDto.getPhone(),
            role,
            userDto.getFirstName(),
            userDto.getLastName(),
            new Date(),
            null);
    return userRepository.save(user);
  }

  @Override
  public User addChild(ChildDto childDto) {
    User parent =
        userRepository
            .findById(Long.parseLong(childDto.getParentId()))
            .orElseThrow(
                () ->
                    new EntityNotFoundException(
                        "Parent not found with id: " + childDto.getParentId()));
    User user =
        new User()
            .setUserName(childDto.getUserName())
            .setEmail(childDto.getEmail())
            .setRole(User.Role.CHILD)
            .setDate_created(new Date())
            .setPassword(this.passwordEncoder.encode(childDto.getPassword()))
            .setFirst_name(childDto.getFirstName())
            .setLast_name(childDto.getLastName())
            .setParent(parent);
    User childUser = userRepository.save(user);

    Child child = new Child().setUser(user).setAgeGroup(childDto.getAgeGroup());

    // Save the Child entity
    childRepository.save(child);
    return childUser;
  }

  @Override
  public List<Child> getChildren(Long parentId) {
    return childRepository.findByUserParentId(parentId);
  }

  @Override
  public User getUser(String email) {
    return userRepository
        .findByEmail(email)
        .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
  }

} // end of class
