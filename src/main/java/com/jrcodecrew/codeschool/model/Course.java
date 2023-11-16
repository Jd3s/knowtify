package com.jrcodecrew.codeschool.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

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

  @ManyToMany
  @JoinTable(
          name = "course_instructor",
          joinColumns = @JoinColumn(name = "course_id"),
          inverseJoinColumns = @JoinColumn(name = "instructor_id", referencedColumnName = "id")
  )
  private Set<Instructor> instructors = new HashSet<>();

  public Course() {
  }

  public Course(
          String courseId,
          String courseName,
          String description,
          AgeGroup ageGroup) {
    this.courseId = courseId;
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

  public Set<Instructor> getInstructors() {
    return instructors;
  }

  public Course setInstructors(Set<Instructor> instructors) {
    this.instructors = instructors;
    return this;
  }

  @Override
  public String toString() {
    return "Course{" +
            "courseId='" + courseId + '\'' +
            ", courseName='" + courseName + '\'' +
            ", description='" + description + '\'' +
            ", ageGroup=" + ageGroup +
            ", instructors=" + instructors +
            '}';
  }
}
