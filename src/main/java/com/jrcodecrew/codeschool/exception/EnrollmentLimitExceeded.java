package com.jrcodecrew.codeschool.exception;

public class EnrollmentLimitExceeded extends RuntimeException{
    public EnrollmentLimitExceeded(String message) {
        super(message);
    }
}
