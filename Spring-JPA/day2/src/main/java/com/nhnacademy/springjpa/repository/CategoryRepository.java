package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.Category;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;

public interface CategoryRepository extends JpaRepository<Category, Integer>, CategoryRepositoryCustom {
  Optional<Category> findById(int id);
}
