package com.nhnacademy.shoppingmall.cart.service;

import com.nhnacademy.shoppingmall.cart.domain.Cart;
import java.util.List;

public interface CartService {

  List<Cart> getUserCartList(String userId);

  boolean isExistProductInCart(int productId, String userId);

  void saveProductInCart(int productId, String userId);

  void deleteProductInCart(int productId, String userId);

  void setQuantityProductInCart(int quantity, int productId, String userId);
}
