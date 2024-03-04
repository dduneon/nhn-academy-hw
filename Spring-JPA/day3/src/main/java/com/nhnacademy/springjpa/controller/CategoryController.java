package com.nhnacademy.springjpa.controller;

import com.nhnacademy.springjpa.domain.CategoryProductDTO;
import com.nhnacademy.springjpa.entity.Category;
import com.nhnacademy.springjpa.entity.CategoryProduct;
import com.nhnacademy.springjpa.entity.Product;
import com.nhnacademy.springjpa.service.CategoryService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/category")
public class CategoryController {
  private final CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping
  public String getCategoryPage(Model model) {
    initCategory(model);
    return "shop/category";
  }

  @PostMapping("/search")
  public String getProductsByCategory(@RequestParam(name="category") int categoryId, Model model) {
    List<Product> productList = categoryService.getProductsByCategory(categoryId);
    model.addAttribute("searchResults", productList);
    initCategory(model);
    return "shop/category";
  }

  @PostMapping("/multisearch")
  public String getProductsByMultiCategory(@RequestParam(name="category") List<Integer> categoryIds, Model model) {
    List<CategoryProduct> categoryProducts = categoryService.getProductsByCategories(categoryIds);
    model.addAttribute("multiSearchResults", categoryProducts);
    initCategory(model);
    return "shop/category";
  }

  public void initCategory(Model model) {
    List<Category> categoryList = categoryService.getAllCategories();
    model.addAttribute("ALL_CATEGORY", categoryList);
  }
}
