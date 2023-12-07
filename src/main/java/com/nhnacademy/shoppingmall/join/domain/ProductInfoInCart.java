package com.nhnacademy.shoppingmall.join.domain;

import java.time.LocalDateTime;

public class ProductInfoInCart {

  // to show product details in shopping cart page
  private int productId;
  private String productImage;
  private String modelName;
  private int unitCost;
  private int quantity;
  private LocalDateTime dateCreated;
  private String userId;

  public ProductInfoInCart(int productId, String productImage, String productName, int unitCost,
      int quantity, LocalDateTime dateCreated, String userId) {
    this.productId = productId;
    this.productImage = productImage;
    this.modelName = productName;
    this.unitCost = unitCost;
    this.quantity = quantity;
    this.dateCreated = dateCreated;
    this.userId = userId;
  }

  public int getProductId() {
    return productId;
  }

  public String getProductImage() {
    return productImage;
  }

  public String getModelName() {
    return modelName;
  }

  public int getUnitCost() {
    return unitCost;
  }

  public int getQuantity() {
    return quantity;
  }

  public LocalDateTime getDateCreated() {
    return dateCreated;
  }

  public String getUserId() {
    return userId;
  }

  public void setProductId(int productId) {
    this.productId = productId;
  }

  public void setProductImage(String productImage) {
    this.productImage = productImage;
  }

  public void setModelName(String modelName) {
    this.modelName = modelName;
  }

  public void setUnitCost(int unitCost) {
    this.unitCost = unitCost;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public void setDateCreated(LocalDateTime dateCreated) {
    this.dateCreated = dateCreated;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
}
