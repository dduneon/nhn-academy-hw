package com.nhnacademy.shoppingmall.orderdetails.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.orderdetails.domain.OrderDetails;
import com.nhnacademy.shoppingmall.orderdetails.repository.OrderDetailsRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderDetailsRepositoryImpl implements OrderDetailsRepository {

  @Override
  public int save(OrderDetails orderDetails) {
    String sql = "insert into OrderDetails values(?, ?, ?, ?)";
    Connection connection = DbConnectionThreadLocal.getConnection();

    try (PreparedStatement psmt = connection.prepareStatement(sql);) {
      psmt.setInt(1, orderDetails.getOrderId());
      psmt.setInt(2, orderDetails.getProductId());
      psmt.setInt(3, orderDetails.getQuantity());
      psmt.setInt(4, orderDetails.getUnitCost());
      log.debug("orderId: {}, productId: {}", orderDetails.getOrderId(), orderDetails.getProductId());

      int result = psmt.executeUpdate();
      log.debug("result: {}", result);
      return result;
    } catch (SQLException sqlException) {
      throw new RuntimeException(sqlException);
    }
  }
}
