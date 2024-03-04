package com.nhnacademy.shoppingmall.categoryproduct.repository;

import java.util.List;

public interface CategoryProductRepository {

  List<String> getCategoriesName(int productId);
}
