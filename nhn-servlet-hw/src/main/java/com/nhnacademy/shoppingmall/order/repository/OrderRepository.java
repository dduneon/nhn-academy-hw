package com.nhnacademy.shoppingmall.order.repository;

import com.nhnacademy.shoppingmall.order.domain.Order;
import java.util.List;

public interface OrderRepository {

  int saveAndReturnOrderId(Order order);

  int getOrderId(Order order);

  List<Order> getAllOrdersByUserId(String userId);
}
