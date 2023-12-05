package com.nhnacademy.shoppingmall.product.service;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.product.domain.Product;
import java.util.List;

public interface ProductService {

  Product getProduct(int productId);

  void saveProduct(Product product);

  void updateProduct(Product product);

  void deleteProduct(int productId);

  List<Product> getAllProductList();

  int getProductCount();

  Page<Product> getProductPage(long offset, long row_count);
}
