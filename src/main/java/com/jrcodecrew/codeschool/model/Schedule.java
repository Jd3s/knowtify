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
    @ManyToOne(fetch = FetchType.LAZY)
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

    @Getter
    @ManyToMany
    @JoinTable(
            name = "enrollment_schedule",
            joinColumns = @JoinColumn(name = "schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "enrollment_id", referencedColumnName = "id")
    )
    private Set<Enrollment> enrollments = new HashSet<>();


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

    public Schedule setEnrollments(Set<Enrollment> enrollments) {
        this.enrollments = enrollments;
        return this;
    }


}