package com.jrcodecrew.codeschool.service.impl;

import com.jrcodecrew.codeschool.dto.LoginDto;
import com.jrcodecrew.codeschool.dto.UserDto;
import com.jrcodecrew.codeschool.model.User;
import com.jrcodecrew.codeschool.repository.UserRepository;
import com.jrcodecrew.codeschool.response.LoginResponse;
import com.jrcodecrew.codeschool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.*;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;
  @Autowired private PasswordEncoder passwordEncoder;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    super();
    this.userRepository = userRepository;
  }

  @Override
  public User saveUser(UserDto userDto) {
    // conversion login for userdto to user
    User user =
        new User(
            userDto.getUserName(),
            this.passwordEncoder.encode(userDto.getPassword()),
            userDto.getEmail(),
            userDto.getPhone(),
            userDto.getRole(),
            userDto.getFirstName(),
            userDto.getLastName(),
            LocalDate.now().toString());
    return userRepository.save(user);
  }

  @Override
  public LoginResponse loginUser(LoginDto loginDto) {

    String msg = "";
    User user = userRepository.findByEmail(loginDto.getEmail());
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
}
