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

  @Column(name = "preview_video", nullable = true)
  private String previewVideo;

  @Column(name = "price", nullable = false)
  private Double price;

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
          AgeGroup ageGroup,
          Double price,
          String previewVideo) {
    this.courseId = courseId;
    this.courseName = courseName;
    this.description = description;
    this.ageGroup = ageGroup;
    this.price = price;
    this.previewVideo = previewVideo;
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

  public String getPreviewVideo() {
    return previewVideo;
  }

  public void setPreviewVideo(String previewVideo) {
    this.previewVideo = previewVideo;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "Course{" +
            "courseId='" + courseId + '\'' +
            ", courseName='" + courseName + '\'' +
            ", description='" + description + '\'' +
            ", ageGroup=" + ageGroup +
            ", price=" + price +
            ", instructors=" + instructors +
            ", previewVideo=" + previewVideo +
            '}';
  }
}
