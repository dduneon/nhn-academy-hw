package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.CartProduct;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CartProductRepository extends JpaRepository<CartProduct, CartProduct.PK> {
  Optional<CartProduct> findByPk_CartId(Long cartId);

  @Modifying
  @Query("update CartProduct cp set cp.quantity=?2 where cp.pk.cartId=?1")
  int updateQuantityByCartId(Long cartId, int quantity);

  void deleteByPk_ProductId(Long productId);
}
