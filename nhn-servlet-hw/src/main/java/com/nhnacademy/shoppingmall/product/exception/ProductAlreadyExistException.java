package com.nhnacademy.shoppingmall.product.exception;

public class ProductAlreadyExistException extends RuntimeException {

  public ProductAlreadyExistException(String message) {
    super(String.format("product already exist:%s", message));
  }
}
