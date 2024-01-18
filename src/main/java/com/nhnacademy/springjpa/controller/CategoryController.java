package com.nhnacademy.springjpa.controller;

import com.nhnacademy.springjpa.entity.Category;
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
    List<Category> categoryList = categoryService.getAllCategories();

    model.addAttribute("ALL_CATEGORY", categoryList);
    return "shop/category";
  }

  @PostMapping("/search")
  public String getProductsByCategory(@RequestParam(name="category") long categoryId, Model model) {
    return "shop/category";
  }

  @PostMapping("/multisearch")
  public String getProductsByMultiCategory(@RequestParam(name="category") long[] categoryIds, Model model) {
    return "shop/category";
  }
}
