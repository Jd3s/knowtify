package com.jrcodecrew.codeschool.dto;

public class LoginDto {

    private String email;
    private String password;

    public LoginDto(String userName, String password) {
        this.email = userName;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginDto{" +
                "userName='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
