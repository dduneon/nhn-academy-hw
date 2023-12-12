package com.nhnacademy.shoppingmall.orderdetails.repository;

import com.nhnacademy.shoppingmall.orderdetails.domain.OrderDetails;

public interface OrderDetailsRepository {

  int save(OrderDetails orderDetails);
}
