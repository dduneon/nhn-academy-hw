package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.CategoryProduct;
import com.nhnacademy.springjpa.entity.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryProductRepository extends JpaRepository<CategoryProduct, CategoryProduct.PK> {
  Optional<CategoryProduct> findByPk_ProductId(Long categoryId, long productId);
  List<CategoryProduct> findByPk_CategoryId(Long categoryId);
}
