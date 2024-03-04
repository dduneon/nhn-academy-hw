package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.Review;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

  List<Review> findByUser_Id(String userId);
  Optional<Review> findById(Long id);
  void deleteById(Long id);
}
