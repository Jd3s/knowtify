package com.jrcodecrew.codeschool.controller;

import com.jrcodecrew.codeschool.model.Enrollment;
import com.jrcodecrew.codeschool.response.UpdatedUserResponse;
import com.jrcodecrew.codeschool.service.EnrollmentService;
import com.jrcodecrew.codeschool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private EnrollmentService enrollmentService;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService) {
        super();
        this.enrollmentService = enrollmentService;
    }


    @GetMapping("/getEnrollmentsForCourse/{courseId}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsForCourse(
            @PathVariable String courseId) {
        List<Enrollment> enrollmentList = enrollmentService.getAllEnrollmentsForCourse(courseId);
        return ResponseEntity.ok(enrollmentList);
    }

}
