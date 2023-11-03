package com.jrcodecrew.codeschool.service;

import com.jrcodecrew.codeschool.dto.LoginDto;
import com.jrcodecrew.codeschool.dto.UserDto;
import com.jrcodecrew.codeschool.model.User;
import com.jrcodecrew.codeschool.response.LoginResponse;

public interface UserService {
    User saveUser(UserDto userDto);

    LoginResponse loginUser(LoginDto loginDto);
}
