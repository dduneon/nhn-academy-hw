package com.nhnacademy.springjpa.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "Categories")
public class Category {
  @Id
  @Column(name = "category_id")
  private int id;

  @Column(name = "category_name")
  private String name;

  @OneToMany(mappedBy = "category")
  private List<CategoryProduct> categoryProduct;
}
