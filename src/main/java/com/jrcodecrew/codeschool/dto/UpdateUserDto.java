package com.jrcodecrew.codeschool.dto;

public class UpdateUserDto {

    private String firstName;
    private String lastName;
    private String ageGroup;


    public UpdateUserDto(String firstName, String lastName, String ageGroup) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ageGroup = ageGroup;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }
}
