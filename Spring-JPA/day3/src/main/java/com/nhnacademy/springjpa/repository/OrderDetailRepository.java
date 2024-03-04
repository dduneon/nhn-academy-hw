package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.OrderDetail;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetail.PK> {
  List<OrderDetail> findByPk_OrderId(Long orderId);
}
