package com.nhnacademy.springjpa.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.AliasFor;

public interface CategoryProductDTO {
  ProductDTO getProduct();
  CategoryDTO getCategory();

  interface ProductDTO {
    String getProductName();
  }
  interface CategoryDTO {
    String getCategoryName();
  }
}
