package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.Category;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class CategoryRepositoryImpl extends QuerydslRepositorySupport implements CategoryRepositoryCustom{

  public CategoryRepositoryImpl() {
    super(Category.class);
  }
}
