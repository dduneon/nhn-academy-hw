package com.nhnacademy.springjpa.controller;

import com.nhnacademy.springjpa.entity.Product;
import com.nhnacademy.springjpa.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/detail")
public class ProductDetailController {
  private final ProductService productService;

  public ProductDetailController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/{productId}")
  public String getProductDetail(@PathVariable long productId, Model model) {
    Product product = productService.getProductDetail(productId);
    return "index";
  }

}
