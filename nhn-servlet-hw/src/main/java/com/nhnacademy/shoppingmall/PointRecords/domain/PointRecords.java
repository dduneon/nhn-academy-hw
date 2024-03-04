package com.nhnacademy.shoppingmall.PointRecords.domain;

import java.time.LocalDateTime;
import java.util.Optional;

public class PointRecords {
  private String userId;
  private int amount;
  private Integer orderId = null;

  private LocalDateTime recordDate;

  public PointRecords(String userId, int amount, Integer orderId, LocalDateTime recordDate) {
    this.userId = userId;
    this.amount = amount;
    this.orderId = orderId;
    this.recordDate = recordDate;
  }

  public String getUserId() {
    return userId;
  }

  public int getAmount() {
    return amount;
  }

  public Optional<Integer> getOrderId() {
    return Optional.ofNullable(orderId);
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public LocalDateTime getRecordDate() {
    return recordDate;
  }

}
