package com.jrcodecrew.codeschool.dto;

public class InstructorDto {
  private String firstName;

  private String lastName;

  private String email;

  private String phone;

  private int yearsOfExperience;
  private String proficiency;

  public InstructorDto() {}

  public InstructorDto(
      String firstName,
      String lastName,
      String email,
      String phone,
      int yearsOfExperience,
      String proficiency) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phone = phone;
    this.yearsOfExperience = yearsOfExperience;
    this.proficiency = proficiency;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public int getYearsOfExperience() {
    return yearsOfExperience;
  }

  public void setYearsOfExperience(int yearsOfExperience) {
    this.yearsOfExperience = yearsOfExperience;
  }

  public String getProficiency() {
    return proficiency;
  }

  public void setProficiency(String proficiency) {
    this.proficiency = proficiency;
  }

  @Override
  public String toString() {
    return "InstructorDto{"
        + "firstName='"
        + firstName
        + '\''
        + ", lastName='"
        + lastName
        + '\''
        + ", email='"
        + email
        + '\''
        + ", phone='"
        + phone
        + '\''
        + ", yearsOfExperience="
        + yearsOfExperience
        + ", proficiency='"
        + proficiency
        + '\''
        + '}';
  }
}
