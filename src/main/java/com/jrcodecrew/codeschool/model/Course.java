package com.jrcodecrew.codeschool.model;

import jakarta.persistence.*;

@Entity
@Table(name = "course")
public class Course {

  @Id
  @Column(name = "course_id", nullable = false)
  private String courseId;

  @Column(name = "course_name", nullable = false)
  private String courseName;

  @Column(name = "description", nullable = false)
  private String description;

  @Enumerated(EnumType.STRING)
  @Column(name = "age_group", nullable = false)
  private AgeGroup ageGroup;

  public Course() {
  }

  public Course(String course_id, String courseName, String description, AgeGroup ageGroup) {
    this.courseId = course_id;
    this.courseName = courseName;
    this.description = description;
    this.ageGroup = ageGroup;
  }

  public String getCourseId() {
    return courseId;
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
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
        + courseId
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
