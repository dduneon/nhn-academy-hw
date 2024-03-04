package com.nhnacademy.springjpa.exception;

public class HouseholdAlreadyExistException extends RuntimeException{

  public HouseholdAlreadyExistException(String message) {
    super(message);
  }
}
