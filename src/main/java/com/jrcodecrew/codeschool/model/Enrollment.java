package com.jrcodecrew.codeschool.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "enrollment")
public class Enrollment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "child_id")
  private Child child;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "course_id")
  private Course course;

  // @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "instructor_id")
  private Instructor instructor;

  @Column(name = "status", nullable = false)
  @Enumerated(EnumType.STRING)
  private EnrollmentStatus status;

  @ManyToOne
  @JoinColumn(name = "schedule_id")
  private Schedule schedule;

  public Enrollment() {}

  public Enrollment(Child child, Course course, Instructor instructor) {
    this.child = child;
    this.course = course;
    this.instructor = instructor;
    this.status = EnrollmentStatus.ACTIVE;
  }

  public Enrollment(Child child, Course course, Instructor instructor, Schedule schedule) {
    this.child = child;
    this.course = course;
    this.instructor = instructor;
    this.status = EnrollmentStatus.PENDING;
    this.schedule = schedule;
  }

  public Child getChild() {
    return child;
  }

  public void setChild(Child child) {
    this.child = child;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public Instructor getInstructor() {
    return instructor;
  }

  public void setInstructor(Instructor instructor) {
    this.instructor = instructor;
  }

  public EnrollmentStatus getStatus() {
    return status;
  }

  public void setStatus(EnrollmentStatus status) {
    this.status = status;
  }

  public Schedule getSchedule() {
    return schedule;
  }

  public void setSchedule(Schedule schedule) {
    this.schedule = schedule;
  }

  @Override
  public String toString() {
    return "Enrollment{"
        + "id="
        + id
        + ", child="
        + child
        + ", course="
        + course
        + ", instructor="
        + instructor
        + ", status="
        + status
        + ", schedule="
        + schedule
        + '}';
  }
}
