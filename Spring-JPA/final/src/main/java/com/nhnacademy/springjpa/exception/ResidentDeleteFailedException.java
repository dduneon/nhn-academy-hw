package com.nhnacademy.springjpa.exception;

public class ResidentDeleteFailedException extends RuntimeException{
  public ResidentDeleteFailedException(String message) {
    super(message);
  }
}
