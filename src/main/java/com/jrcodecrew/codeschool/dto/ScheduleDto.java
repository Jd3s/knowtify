package com.jrcodecrew.codeschool.dto;

import com.jrcodecrew.codeschool.model.EnrollmentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
public class ScheduleDto {

    private LocalTime startTime;
    private LocalTime endTime;
    private int day;
    private Long instructorId;
    private EnrollmentStatus status;
    private int cap;
    private int currently_enrolled;

}
