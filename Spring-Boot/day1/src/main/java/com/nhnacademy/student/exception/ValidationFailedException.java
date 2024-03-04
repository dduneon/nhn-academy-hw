package com.nhnacademy.student.exception;

import javax.validation.ValidationException;

public class ValidationFailedException extends ValidationException {

  public ValidationFailedException(String message) {
    super(message);
  }
}
