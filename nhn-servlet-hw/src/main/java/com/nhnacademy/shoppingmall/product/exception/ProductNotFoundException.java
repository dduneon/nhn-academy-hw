package com.nhnacademy.shoppingmall.product.exception;

public class ProductNotFoundException extends RuntimeException {

  public ProductNotFoundException(String message) {
    super(String.format("product not found:%s", message));
  }
}
