package com.nhnacademy.springjpa.exception;

public class HouseholdMovementAddressAlreadyExistException extends RuntimeException{

  public HouseholdMovementAddressAlreadyExistException(String message) {
    super(message);
  }
}
