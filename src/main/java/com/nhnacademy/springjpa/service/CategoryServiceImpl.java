package com.nhnacademy.springjpa.service;

import com.nhnacademy.springjpa.entity.Category;
import com.nhnacademy.springjpa.entity.Product;
import com.nhnacademy.springjpa.repository.CategoryRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService{
  private final CategoryRepository categoryRepository;

  public CategoryServiceImpl(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public List<Category> getAllCategories() {
    return categoryRepository.findAll();
  }

  public List<Product> getProductsByCategory(long categoryId) {

  }

  public List<Product> getProductsByCategories(long[] categoryId) {

  }

}
