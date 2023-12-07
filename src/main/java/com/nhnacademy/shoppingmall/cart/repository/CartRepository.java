package com.nhnacademy.shoppingmall.cart.repository;

import com.nhnacademy.shoppingmall.cart.domain.Cart;
import java.util.List;

public interface CartRepository {

  public int save(int productId, String userId);

  public int delete(int productId, String userId);

  public int update(int quantity, int productId, String userId);

  public List<Cart> getCart(String userId);

}
