package com.nhnacademy.shoppingmall.order.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Order {

  private int orderId;
  private int customerId;
  private LocalDateTime orderDate;
  private LocalDateTime shipDate;
  private String userId;
  private String shipAddress;

  public Order(int customerId, LocalDateTime orderDate, LocalDateTime shipDate, String userId, String shipAddress) {
    this.customerId = customerId;
    this.orderDate = orderDate;
    this.shipDate = shipDate;
    this.userId = userId;
    this.shipAddress = shipAddress;
  }

  public Order(int orderId, int customerId, LocalDateTime orderDate, LocalDateTime shipDate,
      String userId, String shipAddress) {
    this.orderId = orderId;
    this.customerId = customerId;
    this.orderDate = orderDate;
    this.shipDate = shipDate;
    this.userId = userId;
    this.shipAddress = shipAddress;
  }

  public int getOrderId() {
    return orderId;
  }

  public int getCustomerId() {
    return customerId;
  }

  public LocalDateTime getOrderDate() {
    return orderDate;
  }

  public LocalDateTime getShipDate() {
    return shipDate;
  }

  public String getUserId() {
    return userId;
  }

  public String getShipAddress() {
    return shipAddress;
  }

  public void setShipAddress(String shipAddress) {
    this.shipAddress = shipAddress;
  }

  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }

  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }

  public void setOrderDate(LocalDateTime orderDate) {
    this.orderDate = orderDate;
  }

  public void setShipDate(LocalDateTime shipDate) {
    this.shipDate = shipDate;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
}
