package com.nhnacademy.springjpa.domain;

public interface ProductDTO {
  long getId();
  String getName();
  String getDescription();
  String getImage();
  int getUnitCost();
}
