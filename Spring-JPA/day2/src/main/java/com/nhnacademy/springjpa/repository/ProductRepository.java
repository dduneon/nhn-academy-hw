package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.domain.ProductDTO;
import com.nhnacademy.springjpa.entity.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
  Optional<Product> findById(Long id);
  int countBy();
  int countByNameContains(String name);
  Page<ProductDTO> findAllBy(Pageable pageable);

  Page<ProductDTO> findByNameContains(String name, Pageable pageable);
}
