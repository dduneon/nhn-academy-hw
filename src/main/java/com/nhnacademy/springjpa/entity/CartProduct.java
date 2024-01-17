package com.nhnacademy.springjpa.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CartProduct")
public class CartProduct {
  @EmbeddedId
  private PK pk;

  @Column(name = "quantity")
  private int quantity;

  @Embeddable
  @NoArgsConstructor
  @AllArgsConstructor
  @EqualsAndHashCode
  public static class PK implements Serializable {
    @Column(name = "cart_id")
    private long cartId;
    @Column(name = "product_id")
    private long productId;
  }
}