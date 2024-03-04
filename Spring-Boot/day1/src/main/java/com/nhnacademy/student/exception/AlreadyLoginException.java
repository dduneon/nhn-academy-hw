package com.nhnacademy.student.exception;

public class AlreadyLoginException extends RuntimeException{
  public AlreadyLoginException(String message) {
    super(message);
  }
}
