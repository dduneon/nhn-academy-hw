package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.Order;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
  List<Order> findByUser_Id(String userId);
  Optional<Order> findByIdAndUser_Id(Long id, String userId);
}
