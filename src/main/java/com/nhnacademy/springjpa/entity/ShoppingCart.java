package com.nhnacademy.springjpa.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ShoppingCart")
public class ShoppingCart {
  @Id
  @Column(name = "cart_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long cartId;
  @Column(name = "user_id")
  private String userId;
  @Column(name = "product_id")
  private long productId;
  @Column(name = "Quantity")
  private int quantity;
  @Column(name = "CartCreated")
  private LocalDateTime cartCreated;
}
