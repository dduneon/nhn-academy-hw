package com.nhnacademy.springjpa.exception;

public class ReportAlreadyExistException extends RuntimeException{

  public ReportAlreadyExistException(String message) {
    super(message);
  }
}
