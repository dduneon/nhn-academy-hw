package com.nhnacademy.springjpa.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Table(name = "CartProduct")
public class CartProduct {
  @EmbeddedId
  private PK pk;

  @MapsId("cartId")
  @ManyToOne
  @JoinColumn(name="cart_id")
  private ShoppingCart shoppingCart;

  @MapsId("productId")
  @ManyToOne
  @JoinColumn(name="product_id")
  private Product product;

  @Column(name = "quantity")
  private int quantity;

  @Embeddable
  @NoArgsConstructor
  @AllArgsConstructor
  @EqualsAndHashCode
  @Getter
  @Setter
  public static class PK implements Serializable {
    @Column(name = "cart_id")
    private long cartId;
    @Column(name = "product_id")
    private long productId;
  }
}