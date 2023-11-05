package com.jrcodecrew.codeschool.model;

import jakarta.persistence.*;

@Entity
@Table(name = "course")
public class Course {

  @Id private long course_id;

  @Column(name = "course_name", nullable = false)
  private String courseName;

  @Column(name = "description", nullable = false)
  private String description;

  @Enumerated(EnumType.STRING)
  @Column(name = "age_group", nullable = false)
  private AgeGroup ageGroup;

  public Course(long course_id, String courseName, String description, AgeGroup ageGroup) {
    this.course_id = course_id;
    this.courseName = courseName;
    this.description = description;
    this.ageGroup = ageGroup;
  }

  public long getCourse_id() {
    return course_id;
  }

  public void setCourse_id(long course_id) {
    this.course_id = course_id;
  }

  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public AgeGroup getAgeGroup() {
    return ageGroup;
  }

  public void setAgeGroup(AgeGroup ageGroup) {
    this.ageGroup = ageGroup;
  }

  @Override
  public String toString() {
    return "Course{"
        + "course_id="
        + course_id
        + ", courseName='"
        + courseName
        + '\''
        + ", description='"
        + description
        + '\''
        + ", ageGroup="
        + ageGroup
        + '}';
  }
}
