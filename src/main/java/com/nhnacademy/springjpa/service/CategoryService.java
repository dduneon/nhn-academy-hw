package com.nhnacademy.springjpa.service;

import com.nhnacademy.springjpa.entity.Category;
import com.nhnacademy.springjpa.entity.Product;
import java.util.List;

public interface CategoryService {
  List<Category> getAllCategories();
  List<Product> getProductsByCategory(long categoryId);
  List<Product> getProductsByCategories(long[] categoryId);

}
