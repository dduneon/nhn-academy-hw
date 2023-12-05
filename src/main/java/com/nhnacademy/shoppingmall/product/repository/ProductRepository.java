package com.nhnacademy.shoppingmall.product.repository;

import com.nhnacademy.shoppingmall.product.domain.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {

  Optional<Product> findByProductId(int productId);

  List<Product> getAllProductList();

  List<Product> getLimitedList(long offset, long row_count);

  // 생각해보기, CategoryProduct 에서 구현 하는 것이 좋을듯
  List<Product> getListByCategoryId(int categoryId);

  int getProductCount();

  int save(Product product);

  int deleteByProductId(int productId);

  int update(Product product);
}
