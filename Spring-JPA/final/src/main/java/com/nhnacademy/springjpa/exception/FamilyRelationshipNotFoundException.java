package com.nhnacademy.springjpa.exception;

public class FamilyRelationshipNotFoundException extends RuntimeException{

  public FamilyRelationshipNotFoundException(String message) {
    super(message);
  }
}
