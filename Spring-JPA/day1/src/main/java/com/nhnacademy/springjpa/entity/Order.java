package com.nhnacademy.springjpa.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Orders")
public class Order {
  @Id
  @Column(name = "order_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "order_date")
  private LocalDateTime orderDate;
  @Column(name = "ship_date")
  private LocalDateTime shipDate;
  @Column(name = "user_id")
  private String userId;
  @Column(name = "ship_address")
  private String shipAddress;
}
