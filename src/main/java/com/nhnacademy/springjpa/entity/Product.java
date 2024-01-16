package com.nhnacademy.springjpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Products")
public class Product {
  @Id
  @Column(name = "product_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "product_number")
  private String number;
  @Column(name = "product_name")
  private String name;
  @Column(name = "product_image")
  private String image;
  @Column(name = "unit_cost")
  private int unitCost;
  @Column(name = "description")
  private String description;

}
