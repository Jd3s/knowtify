package com.jrcodecrew.codeschool.controller;

import com.jrcodecrew.codeschool.dto.EnrollmentDto;
import com.jrcodecrew.codeschool.model.Order;
import com.jrcodecrew.codeschool.service.CourseService;
import com.jrcodecrew.codeschool.service.EnrollmentService;
import com.jrcodecrew.codeschool.service.PaypalService;
import com.jrcodecrew.codeschool.service.impl.PaypalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.jrcodecrew.codeschool.model.Course;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/paypal")
public class PaypalController {

    @Autowired
    PaypalServiceImpl service;

    @Autowired
    EnrollmentService enrollmentService;

    public static final String SUCCESS_URL = "/pay/success";
    public static final String CANCEL_URL = "/pay/cancel";

    private CourseService courseService;

    @Autowired
    public PaypalController(CourseService courseService) {
        super();
        this.courseService = courseService;
    }


    @PostMapping("/pay")
    public String payment(@RequestBody Order order) {

        try {
            Course course = courseService.getCourseById(order.getEnrollmentDetails().getCourseId());
            order.setPrice(course.getPrice());

            Payment paymentReq = service.createPayment(order.getPrice(), order.getCurrency(), order.getMethod(),
                    order.getIntent(), order.getDescription(), "http://localhost:8080/api/paypal" + CANCEL_URL,
                    "http://localhost:8080/api/paypal" + SUCCESS_URL + "/" + order.getEnrollmentDetails().getCourseId() + "/" + order.getEnrollmentDetails().getChildId() + "/" + order.getEnrollmentDetails().getInstructorId()  + "/" + order.getEnrollmentDetails().getScheduleId() );
            for(Links link:paymentReq.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    return link.getHref();
                }
              }
             } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }

        return "Failed";
    }

    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "cancel";
    }

    @GetMapping(value = SUCCESS_URL+"/{courseId}/{childId}/{instructorId}/{scheduleId}")
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, @PathVariable("courseId") String courseId, @PathVariable("childId") Long childId,@PathVariable("instructorId") Long instructorId, @PathVariable("scheduleId") Long scheduleId) {
        try {
            Payment payment = service.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                EnrollmentDto enrollmentDto = new EnrollmentDto(childId,courseId, instructorId,scheduleId);
                enrollmentService.enrollChild(enrollmentDto);
                return "success";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }

}