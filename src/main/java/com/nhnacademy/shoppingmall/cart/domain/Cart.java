package com.nhnacademy.shoppingmall.cart.domain;

import java.time.LocalDateTime;

public class Cart {

  private int recordId;
  private int quantity;
  private int productId;
  private LocalDateTime dateCreated;
  private String userId;

  public Cart(int recordId, int quantity, int productId, LocalDateTime dateCreated, String userId) {
    this.recordId = recordId;
    this.quantity = quantity;
    this.productId = productId;
    this.dateCreated = dateCreated;
    this.userId = userId;
  }

  public int getQuantity() {
    return quantity;
  }

  public int getProductId() {
    return productId;
  }

  public LocalDateTime getDateCreated() {
    return dateCreated;
  }

  public String getUserId() {
    return userId;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public void setProductId(int productId) {
    this.productId = productId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
}
