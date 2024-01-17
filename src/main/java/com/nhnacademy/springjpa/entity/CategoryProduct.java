package com.nhnacademy.springjpa.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "CategoryProducts")
public class CategoryProduct {
  @EmbeddedId
  private PK pk;

  @AllArgsConstructor
  @NoArgsConstructor
  @Embeddable
  @EqualsAndHashCode
  @Getter
  public static class PK implements Serializable{
    @Column(name = "category_id")
    private long categoryId;
    @Column(name = "product_id")
    private long productId;
  }
}
