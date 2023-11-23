package com.jrcodecrew.codeschool.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "schedule")
@Getter
@Setter
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    private LocalTime startTime;
    private LocalTime endTime;

    private int day;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status;  // optional - more like a toggle for the schedule of the instructor

    private int cap;
    private int currently_enrolled;

//    @Getter
//    @ManyToMany
//    @JoinTable(
//            name = "enrollment_schedule",
//            joinColumns = @JoinColumn(name = "schedule_id"),
//            inverseJoinColumns = @JoinColumn(name = "enrollment_id", referencedColumnName = "id")
//    )
//    private Set<Enrollment> enrollments = new HashSet<>();


    public Schedule() {}

    public Schedule(Instructor instructor, LocalTime startTime, LocalTime endTime, int day, int cap, int currently_enrolled) {
        this.instructor = instructor;
        this.startTime = startTime;
        this.endTime = endTime;
        this.day = day;
        this.status = EnrollmentStatus.ACTIVE;
        this.cap = cap;
        this.currently_enrolled = 0;
    }

//    public Schedule setEnrollments(Set<Enrollment> enrollments) {
//        this.enrollments = enrollments;
//        return this;
//    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }

    public int getCap() {
        return cap;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }

    public int getCurrently_enrolled() {
        return currently_enrolled;
    }

    public void setCurrently_enrolled(int currently_enrolled) {
        this.currently_enrolled = currently_enrolled;
    }

//    public Set<Enrollment> getEnrollments() {
//        return enrollments;
//    }
}