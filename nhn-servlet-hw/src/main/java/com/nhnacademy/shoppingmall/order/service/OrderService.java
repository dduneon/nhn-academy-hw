package com.nhnacademy.shoppingmall.order.service;

import com.nhnacademy.shoppingmall.order.domain.Order;
import java.util.List;

public interface OrderService {

  int processOrderAndGetOrderId(Order order);

  int getOrderId(Order order);

  List<Order> getAllOrders(String userId);
}
