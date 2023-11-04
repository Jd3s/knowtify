package com.jrcodecrew.codeschool.exception;

public class PhoneNumberException extends RuntimeException {
    public PhoneNumberException() {
        super("Phone number cannot be null");
    }
}
