package com.jrcodecrew.codeschool.exception;

public class EmailNotExistsException extends RuntimeException{
    public EmailNotExistsException() {
        super("Email does not exist!");
    }
}
