package com.nhnacademy.shoppingmall.categoryproduct.service.impl;

import com.nhnacademy.shoppingmall.categoryproduct.repository.CategoryProductRepository;
import com.nhnacademy.shoppingmall.categoryproduct.service.CategoryProductService;
import java.util.List;
import java.util.Objects;

public class CategoryProductServiceImpl implements CategoryProductService {

  private final CategoryProductRepository categoryProductRepository;

  public CategoryProductServiceImpl(CategoryProductRepository categoryProductRepository) {
    this.categoryProductRepository = categoryProductRepository;
  }

  @Override
  public List<String> getCategoriesName(int productId) {
    List<String> categories = categoryProductRepository.getCategoriesName(productId);
    if(Objects.isNull(categories)) {
      throw new RuntimeException("categories is null");
    }
    return categories;
  }
}
