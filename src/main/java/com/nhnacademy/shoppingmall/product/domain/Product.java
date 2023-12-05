package com.nhnacademy.shoppingmall.product.domain;

import java.util.Objects;

public class Product {

  private int productId;
  private int categoryId;
  private String modelNumber;
  private String modelName;
  private String productImage;
  private int unitCost;
  private String description;

  public Product(int productId, int categoryId, String modelNumber, String modelName,
      String productImage, int unitCost, String description) {
    this.productId = productId;
    this.categoryId = categoryId;
    this.modelNumber = modelNumber;
    this.modelName = modelName;
    this.productImage = productImage;
    this.unitCost = unitCost;
    this.description = description;
  }

  public int getProductId() {
    return productId;
  }

  public int getCategoryId() {
    return categoryId;
  }

  public String getModelNumber() {
    return modelNumber;
  }

  public String getModelName() {
    return modelName;
  }

  public String getProductImage() {
    return productImage;
  }

  public int getUnitCost() {
    return unitCost;
  }

  public String getDescription() {
    return description;
  }

  public void setProductId(int productId) {
    this.productId = productId;
  }

  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
  }

  public void setModelNumber(String modelNumber) {
    this.modelNumber = modelNumber;
  }

  public void setModelName(String modelName) {
    this.modelName = modelName;
  }

  public void setProductImage(String productImage) {
    this.productImage = productImage;
  }

  public void setUnitCost(int unitCost) {
    this.unitCost = unitCost;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Product product = (Product) o;
    return Objects.equals(productId, product.productId) && Objects.equals(
        modelNumber, product.modelNumber) && Objects.equals(modelName, product.modelName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productId, modelNumber, modelName);
  }

  @Override
  public String toString() {
    return "Product{" +
        "productId='" + productId + '\'' +
        ", categoryId='" + categoryId + '\'' +
        ", modelNumber='" + modelNumber + '\'' +
        ", modelName='" + modelName + '\'' +
        ", unitCost=" + unitCost + '\'' +
        ", description=" + description + '}';
  }
}
