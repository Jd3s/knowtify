package com.jrcodecrew.codeschool.exception;

public class LoginFailedException extends RuntimeException{

    public LoginFailedException() {
        super("Login Failed!");
    }
}
