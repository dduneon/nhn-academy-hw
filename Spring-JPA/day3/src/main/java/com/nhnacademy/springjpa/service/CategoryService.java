package com.nhnacademy.springjpa.service;

import com.nhnacademy.springjpa.domain.CategoryProductDTO;
import com.nhnacademy.springjpa.entity.Category;
import com.nhnacademy.springjpa.entity.CategoryProduct;
import com.nhnacademy.springjpa.entity.Product;
import java.util.List;

public interface CategoryService {
  List<Category> getAllCategories();
  List<Product> getProductsByCategory(int categoryId);

  List<CategoryProduct> getProductsByCategories(List<Integer> categoryId);

}
