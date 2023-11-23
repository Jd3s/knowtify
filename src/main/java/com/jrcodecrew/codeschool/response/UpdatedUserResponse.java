package com.jrcodecrew.codeschool.response;

import com.jrcodecrew.codeschool.model.AgeGroup;
import com.jrcodecrew.codeschool.model.Child;
import com.jrcodecrew.codeschool.model.User;

public class UpdatedUserResponse {
  private long userId;
  private String firstName;
  private String lastName;
  private User.Role role;
  private AgeGroup ageGroup; // Specific to Child

  // Constructor, getters, and setters

  public UpdatedUserResponse(
      long userId, String firstName, String lastName, User.Role role) {
    this.userId = userId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.role = role;
  }


  public UpdatedUserResponse() {
  }

  public long getUserId() {
    return userId;
  }

  public UpdatedUserResponse setUserId(long userId) {
    this.userId = userId;
    return this;
  }

  public String getFirstName() {
    return firstName;
  }

  public UpdatedUserResponse setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public UpdatedUserResponse setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public User.Role getRole() {
    return role;
  }

  public UpdatedUserResponse setRole(User.Role role) {
    this.role = role;
    return this;
  }

  public AgeGroup getAgeGroup() {
    return ageGroup;
  }

  public void setAgeGroup(AgeGroup ageGroup) {
    this.ageGroup = ageGroup;
  }

  public static UpdatedUserResponse fromUserAndChild(User user, Child child) {
    UpdatedUserResponse response =
        new UpdatedUserResponse(
            user.getId(),
            user.getFirstName(),
            user.getLastName(),
            user.getRole());

    if (child != null) {
      response.setAgeGroup(child.getAgeGroup());
    }

    return response;
  }
}
