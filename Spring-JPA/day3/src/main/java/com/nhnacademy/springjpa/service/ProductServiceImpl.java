package com.nhnacademy.springjpa.service;

import com.nhnacademy.springjpa.domain.ProductDTO;
import com.nhnacademy.springjpa.entity.Product;
import com.nhnacademy.springjpa.exception.ProductNotFoundException;
import com.nhnacademy.springjpa.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{
  private final ProductRepository productRepository;

  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Page<ProductDTO> getPageProducts(Pageable pageable) {
    return productRepository.findAllBy(pageable);
  }

  public Page<ProductDTO> getPageProductsByName(Pageable pageable, String name) {
    return productRepository.findByNameContains(name, pageable);
  }

  public int getProductCount() {
    return productRepository.countBy();
  }

  public int getProductCountByName(String name) {
    return productRepository.countByNameContains(name);
  }

  public Product getProductDetail(long productId) {
    Optional<Product> product = productRepository.findById(productId);
    if(product.isPresent())
      return product.get();
    throw new ProductNotFoundException();
  }
}
