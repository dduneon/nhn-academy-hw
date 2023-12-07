package com.nhnacademy.shoppingmall.cart.service.impl;

import com.nhnacademy.shoppingmall.cart.repository.CartRepository;
import com.nhnacademy.shoppingmall.cart.service.CartService;
import com.nhnacademy.shoppingmall.join.domain.ProductInfoInCart;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CartServiceImpl implements CartService {

  private final CartRepository cartRepository;

  public CartServiceImpl(CartRepository cartRepository) {
    this.cartRepository = cartRepository;
  }

  /**
   * @param userId
   * @return
   */
  @Override
  public List<ProductInfoInCart> getUserCartList(String userId) {
    List<ProductInfoInCart> cart = cartRepository.getProductInfoInCart(userId);
    log.debug("get cart length: {}", cart.size());
    if (Objects.isNull(cart)) {
      log.error("cart is null");
      throw new RuntimeException("cart is null");
    }
    return cart;
  }

  /**
   * @param productId
   * @param userId
   * @return
   */
  @Override
  public boolean isExistProductInCart(int productId, String userId) {
    int result = cartRepository.countByProductIdUserId(productId, userId);
    return result > 0;
  }

  /**
   * @param productId
   * @param userId
   */
  @Override
  public void saveProductInCart(int productId, String userId) {
    int result = cartRepository.save(productId, userId);
    if (result < 1) {
      log.error("cannot save in cart");
      throw new RuntimeException("cannot save in cart");
    }
  }

  /**
   * @param productId
   * @param userId
   */
  @Override
  public void deleteProductInCart(int productId, String userId) {
    int result = cartRepository.delete(productId, userId);
    if (result < 1) {
      log.error("cannot delete in cart");
      throw new RuntimeException("cannot delete in cart");
    }
  }

  /**
   * @param quantity
   * @param productId
   * @param userId
   */
  @Override
  public void setQuantityProductInCart(int quantity, int productId, String userId) {
    int result = cartRepository.update(quantity, productId, userId);
    if (result < 1) {
      log.error("cannot update in cart");
      throw new RuntimeException("cannot update in cart");
    }
  }
}
