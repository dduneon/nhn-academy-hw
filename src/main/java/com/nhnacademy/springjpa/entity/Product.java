package com.nhnacademy.springjpa.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "Products")
public class Product {
  @Id
  @Column(name = "product_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
  private List<CartProduct> cartProduct;

  @OneToMany(mappedBy = "product")
  private List<CategoryProduct> categoryProduct;

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
