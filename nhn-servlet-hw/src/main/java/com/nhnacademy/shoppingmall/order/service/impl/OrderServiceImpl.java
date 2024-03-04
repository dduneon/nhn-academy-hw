package com.nhnacademy.shoppingmall.order.service.impl;

import com.nhnacademy.shoppingmall.order.domain.Order;
import com.nhnacademy.shoppingmall.order.repository.OrderRepository;
import com.nhnacademy.shoppingmall.order.service.OrderService;
import java.util.List;
import java.util.Objects;

public class OrderServiceImpl implements OrderService {
  private final OrderRepository orderRepository;

  public OrderServiceImpl(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Override
  public int processOrderAndGetOrderId(Order order) {
    if(Objects.isNull(order)) {
      throw new RuntimeException("order is null");
    }
    return orderRepository.saveAndReturnOrderId(order);
  }

  @Override
  public int getOrderId(Order order) {
    if(Objects.isNull(order)) {
      throw new RuntimeException("order is null");
    }
    int result = orderRepository.getOrderId(order);
    if(result < 1) {
      throw new RuntimeException("order not found");
    }
    return result;
  }

  @Override
  public List<Order> getAllOrders(String userId) {
    return orderRepository.getAllOrdersByUserId(userId);
  }
}
