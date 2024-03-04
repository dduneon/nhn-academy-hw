package com.nhnacademy.springjpa.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PointRecords")
public class PointRecord {
  @Id
  @Column(name = "user_id")
  private String userId;
  @Column(name = "order_id")
  private long orderId;
  @Column(name = "user_amount")
  private int amount;
  @Column(name = "record_date")
  private LocalDateTime recordDate;

}
