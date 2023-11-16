package com.jrcodecrew.codeschool.dto;

import com.jrcodecrew.codeschool.model.EnrollmentStatus;

public class EnrollmentDto {

  private Long childId;
  private String courseId;
  private Long instructorId;
  private EnrollmentStatus status;

  public EnrollmentDto() {}

  public Long getChildId() {
    return childId;
  }

  public void setChildId(Long childId) {
    this.childId = childId;
  }

  public String getCourseId() {
    return courseId;
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }

  public Long getInstructorId() {
    return instructorId;
  }

  public void setInstructorId(Long instructorId) {
    this.instructorId = instructorId;
  }

  public EnrollmentStatus getStatus() {
    return status;
  }

  public EnrollmentDto setStatus(EnrollmentStatus status) {
    this.status = status;
    return this;
  }

  @Override
  public String toString() {
    return "EnrollmentDto{"
        + "childId="
        + childId
        + ", courseId='"
        + courseId
        + '\''
        + ", instructorId='"
        + instructorId
        + '\''
        + '}';
  }
}
