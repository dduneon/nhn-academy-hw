package com.nhnacademy.account.backend.exception;

public class AccountNotFoundException extends RuntimeException{

  public AccountNotFoundException(String message) {
    super(message);
  }
}
