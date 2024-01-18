package com.nhnacademy.springjpa.service;

import com.nhnacademy.springjpa.domain.ProductDTO;
import com.nhnacademy.springjpa.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
  Page<ProductDTO> getPageProducts(Pageable pageable);
  Page<ProductDTO> getPageProductsByName(Pageable pageable, String name);

  int getProductCount();
  int getProductCountByName(String name);


  Product getProductDetail(long productId);
  }
