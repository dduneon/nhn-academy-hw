package com.nhnacademy.springjpa.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name="PointRecords")
public class PointRecord {
  @Id
  @Column(name="record_id")
  private long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "order_id")
  private Order order;
  @Column(name = "amount")
  private int amount;
  @Column(name = "record_date")
  private LocalDateTime recordDate;

}
