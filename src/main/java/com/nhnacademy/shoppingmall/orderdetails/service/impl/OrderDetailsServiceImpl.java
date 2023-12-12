package com.nhnacademy.shoppingmall.orderdetails.service.impl;

import com.nhnacademy.shoppingmall.orderdetails.domain.OrderDetails;
import com.nhnacademy.shoppingmall.orderdetails.repository.OrderDetailsRepository;
import com.nhnacademy.shoppingmall.orderdetails.service.OrderDetailsService;
import java.util.Objects;

public class OrderDetailsServiceImpl implements OrderDetailsService {
  private final OrderDetailsRepository orderDetailsRepository;

  public OrderDetailsServiceImpl(OrderDetailsRepository orderDetailsRepository) {
    this.orderDetailsRepository = orderDetailsRepository;
  }

  @Override
  public void processOrderDetail(OrderDetails orderDetails) {
    if(Objects.isNull(orderDetails)) {
      throw new RuntimeException("orderdetail is null");
    }
    int result = orderDetailsRepository.save(orderDetails);
    if(result < 1) {
      throw new RuntimeException("cannot process orderdetail");
    }
  }
}
