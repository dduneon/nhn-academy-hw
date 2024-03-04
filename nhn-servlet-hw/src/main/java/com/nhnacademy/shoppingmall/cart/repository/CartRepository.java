package com.nhnacademy.shoppingmall.cart.repository;

import com.nhnacademy.shoppingmall.join.domain.ProductInfoInCart;
import java.util.List;

public interface CartRepository {

  public int save(int productId, String userId);

  public int delete(int productId, String userId);

  public int update(int quantity, int productId, String userId);

  public List<ProductInfoInCart> getProductInfoInCart(String userId);

  public int countByProductIdUserId(int productId, String userId);

  public int getTotalPriceInCart(String userId);
}
