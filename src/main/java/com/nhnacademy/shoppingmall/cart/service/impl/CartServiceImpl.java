package com.nhnacademy.shoppingmall.cart.service.impl;

import com.nhnacademy.shoppingmall.cart.domain.Cart;
import com.nhnacademy.shoppingmall.cart.service.CartService;
import com.nhnacademy.shoppingmall.product.repository.ProductRepository;
import java.util.List;

public class CartServiceImpl implements CartService {

  private final ProductRepository productRepository;

  public CartServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  /**
   * @param userId
   * @return
   */
  @Override
  public List<Cart> getUserCartList(String userId) {
    return null;
  }

  /**
   * @param productId
   * @param userId
   * @return
   */
  @Override
  public boolean isExistProductInCart(int productId, String userId) {
    List<Cart> cart = getUserCartList(userId);
    return false;
  }

  /**
   * @param productId
   * @param userId
   */
  @Override
  public void saveProductInCart(int productId, String userId) {

  }

  /**
   * @param productId
   * @param userId
   */
  @Override
  public void deleteProductInCart(int productId, String userId) {

  }

  /**
   * @param quantity
   * @param productId
   * @param userId
   */
  @Override
  public void setQuantityProductInCart(int quantity, int productId, String userId) {

  }
}
