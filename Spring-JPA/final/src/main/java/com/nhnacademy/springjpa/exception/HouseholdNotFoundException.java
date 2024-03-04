package com.nhnacademy.springjpa.exception;

public class HouseholdNotFoundException extends RuntimeException{

  public HouseholdNotFoundException(String message) {
    super(message);
  }
}
