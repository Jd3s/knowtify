package com.jrcodecrew.codeschool.exception;

public class MultipleActiveEnrollmentsException extends RuntimeException {
  public MultipleActiveEnrollmentsException() {
    super("Multiple active enrollment for child not allowed!");
  }
}
