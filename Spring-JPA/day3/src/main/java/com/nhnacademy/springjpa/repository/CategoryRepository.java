package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.Category;
import com.nhnacademy.springjpa.entity.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;

public interface CategoryRepository extends JpaRepository<Category, Integer>, CategoryRepositoryCustom {
  Optional<Category> findById(int id);

  @Query("select p from Product p join fetch p.categoryProduct cp join fetch cp.category c where c.id=?1")
  List<Product> findProductsByCategory(int categoryId);
}
