package com.nhnacademy.shoppingmall.orderdetails.domain;

public class OrderDetails {
  private int orderId;
  private int productId;
  private int quantity;
  private int unitCost;

  public OrderDetails(int orderId, int productId, int quantity, int unitCost) {
    this.orderId = orderId;
    this.productId = productId;
    this.quantity = quantity;
    this.unitCost = unitCost;
  }

  public int getOrderId() {
    return orderId;
  }

  public int getProductId() {
    return productId;
  }

  public int getQuantity() {
    return quantity;
  }

  public int getUnitCost() {
    return unitCost;
  }

  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }

  public void setProductId(int productId) {
    this.productId = productId;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public void setUnitCost(int unitCost) {
    this.unitCost = unitCost;
  }
}
