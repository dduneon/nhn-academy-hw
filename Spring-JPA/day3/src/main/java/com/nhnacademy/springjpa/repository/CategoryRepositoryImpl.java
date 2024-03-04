package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.domain.CategoryProductDTO;
import com.nhnacademy.springjpa.entity.Category;
import com.nhnacademy.springjpa.entity.CategoryProduct;
import com.nhnacademy.springjpa.entity.QCategory;
import com.nhnacademy.springjpa.entity.QCategoryProduct;
import com.nhnacademy.springjpa.entity.QProduct;
import com.querydsl.core.types.Projections;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class CategoryRepositoryImpl extends QuerydslRepositorySupport implements CategoryRepositoryCustom{

  public CategoryRepositoryImpl() {
    super(Category.class);
  }

  @Override
  public List<CategoryProduct> getItemsHavingCategories(List<Integer> categoryId) {
    QCategoryProduct categoryProduct = QCategoryProduct.categoryProduct;
    QCategory category = QCategory.category;
    QProduct product = QProduct.product;

    List<CategoryProduct> result = from(categoryProduct)
        .join(categoryProduct.category, category)
        .join(categoryProduct.product, product)
        .where(category.id.in(categoryId))
        .select(categoryProduct)
        .distinct()
        .fetch();

    return result;
  }

}
