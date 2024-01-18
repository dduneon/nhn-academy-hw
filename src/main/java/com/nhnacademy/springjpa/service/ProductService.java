package com.nhnacademy.springjpa.service;

import com.nhnacademy.springjpa.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
  Page<Product> getPageProducts(Pageable pageable);
}
