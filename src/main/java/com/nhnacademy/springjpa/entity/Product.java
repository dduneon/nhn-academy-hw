package com.nhnacademy.springjpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Product")
public class Product {
  @Id
  @Column(name = "product_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long productId;
  @Column(name = "ProductNumber")
  private String productNumber;
  @Column(name = "ProductName")
  private String productName;
  @Column(name = "ProductImage")
  private String productImage;
  @Column(name = "UnitCost")
  private int unitCost;
  @Column(name = "Description")
  private String description;

}
