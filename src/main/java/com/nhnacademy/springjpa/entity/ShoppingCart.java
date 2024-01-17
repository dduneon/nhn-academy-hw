package com.nhnacademy.springjpa.entity;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ShoppingCarts")
public class ShoppingCart {
  @Id
  @Column(name = "cart_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.REMOVE)
  private List<CartProduct> cartProduct;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
  @Column(name = "cart_created_at")
  private LocalDateTime createdAt;
}
