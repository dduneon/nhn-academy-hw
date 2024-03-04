package com.nhnacademy.springjpa.exception;

public class ResidentAlreadyExistException extends RuntimeException{

  public ResidentAlreadyExistException(String message) {
    super(message);
  }
}
