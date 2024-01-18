package com.nhnacademy.springjpa.service;

import com.nhnacademy.springjpa.entity.Product;
import com.nhnacademy.springjpa.repository.ProductRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{
  private final ProductRepository productRepository;
  private static final int PAGE_SIZE = 9;

  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Page<Product> getPageProducts(Pageable pageable) {
    return productRepository.findAll(pageable);
  }
}
