package com.jrcodecrew.codeschool.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "instructor")
public class Instructor {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "phone", unique = true)
  private String phone;

  @Column(name = "years_of_experience", nullable = false)
  private int yearsOfExperience;

  @Column(name = "proficiency", nullable = false)
  private String proficiency;

  public Instructor() {}

  public Instructor(
      String firstName,
      String lastName,
      String email,
      String phone,
      int yearsOfExperience,
      String proficiency) {
    this.id = id;
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
    return "Instructor{"
        + "id="
        + id
        + ", firstName='"
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
        + '}';
  }
}
