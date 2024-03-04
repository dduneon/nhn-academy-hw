package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.domain.CategoryProductDTO;
import com.nhnacademy.springjpa.entity.CategoryProduct;
import java.util.List;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CategoryRepositoryCustom {
  List<CategoryProduct> getItemsHavingCategories(List<Integer> categoryId);

}
