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

  @ManyToMany(mappedBy = "enrollments")
  private Set<Schedule> schedules = new HashSet<>();

  public Enrollment() {}

  public Enrollment(Child child, Course course, Instructor instructor) {
    this.child = child;
    this.course = course;
    this.instructor = instructor;
    this.status = EnrollmentStatus.ACTIVE;
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
  public void setSchedule(Set<Schedule> schedules) {
    this.schedules = schedules;
  }

  public Set<Schedule> getSchedule() {
    return schedules;
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
        + '}';
  }
}
