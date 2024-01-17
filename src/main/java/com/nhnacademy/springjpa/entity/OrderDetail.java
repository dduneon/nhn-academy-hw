package com.nhnacademy.springjpa.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "OrderDetails")
public class OrderDetail {

  @EmbeddedId
  private PK pk;

  @Column(name = "quantity")
  private int quantity;
  @Column(name = "unit_cost")
  private int unitCost;

  @NoArgsConstructor
  @AllArgsConstructor
  @EqualsAndHashCode
  @Embeddable
  @Getter
  public static class PK implements Serializable {
    @Column(name = "order_id")
    private long orderId;
    @Column(name = "product_id")
    private long productId;
  }
}
