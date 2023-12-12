package com.nhnacademy.shoppingmall.categoryproduct.domain;

public class CategoryProduct {
 private int categoryId;
 private int productId;

  public CategoryProduct(int categoryId, int productId) {
    this.categoryId = categoryId;
    this.productId = productId;
  }

  public int getCategoryId() {
    return categoryId;
  }

  public int getProductId() {
    return productId;
  }

  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
  }

  public void setProductId(int productId) {
    this.productId = productId;
  }
}
