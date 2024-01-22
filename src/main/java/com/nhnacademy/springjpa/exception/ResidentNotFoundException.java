package com.nhnacademy.springjpa.exception;

public class ResidentNotFoundException extends RuntimeException{

  public ResidentNotFoundException() {
  }

  public ResidentNotFoundException(String message) {
    super(message);
  }
}
