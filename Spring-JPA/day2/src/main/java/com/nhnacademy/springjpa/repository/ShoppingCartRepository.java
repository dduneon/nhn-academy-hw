package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.ShoppingCart;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
  Optional<ShoppingCart> findById(Long id);
  void deleteById(Long id);
}
