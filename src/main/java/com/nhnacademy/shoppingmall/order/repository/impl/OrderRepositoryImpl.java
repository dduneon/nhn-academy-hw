package com.nhnacademy.shoppingmall.order.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.order.domain.Order;
import com.nhnacademy.shoppingmall.order.repository.OrderRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderRepositoryImpl implements OrderRepository {

  @Override
  public int saveAndReturnOrderId(Order order) {
    String sql = "insert into Orders(CustomerID, OrderDate, ShipDate, UserID, ShipAddress) values(?,?,?,?,?)";
    log.debug("sql: {}");
    Connection connection = DbConnectionThreadLocal.getConnection();

    ResultSet rs = null;
    try (PreparedStatement psmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

      psmt.setInt(1, order.getCustomerId());
      psmt.setTimestamp(2, Timestamp.valueOf(order.getOrderDate()));
      psmt.setTimestamp(3, Timestamp.valueOf(order.getShipDate()));
      psmt.setString(4, order.getUserId());
      psmt.setString(5, order.getShipAddress());
      log.debug("user: {}, order saving", order.getUserId());
      int result = psmt.executeUpdate();
      if(result < 1) {
        throw new RuntimeException("fail to save");
      }
      rs = psmt.getGeneratedKeys();
      if(rs.next()) {
        int generatedOrderId = rs.getInt(1);
        log.debug("orderId: {}", generatedOrderId);
        return generatedOrderId;
      } else {
        throw new RuntimeException("fail to get generated order");
      }
    } catch (SQLException sqlException) {
      throw new RuntimeException("fail to save");
    } finally {
      try {
        rs.close();
      } catch (SQLException sqlException) {
        throw new RuntimeException(sqlException);
      }
    }
  }

  @Override
  public int getOrderId(Order order) {
    String sql = "select OrderID from Orders where OrderDate=? and UserID=? and ShipAddress=?";
    Connection connection = DbConnectionThreadLocal.getConnection();
    log.debug("sql: {}", sql);
    int result = -1;
    ResultSet rs = null;
    try (PreparedStatement psmt = connection.prepareStatement(sql);) {
      psmt.setTimestamp(1, Timestamp.valueOf(order.getOrderDate()));
      psmt.setString(2, order.getUserId());
      psmt.setString(3, order.getShipAddress());

      rs = psmt.executeQuery();
      if(rs.next()) {
        result = rs.getInt(1);
      }
      log.debug("result: {}", result);

    } catch (SQLException sqlException) {
      throw new RuntimeException("fail to get orderid");
    } finally {
      try {
        rs.close();
      } catch (SQLException sqlException) {
        throw new RuntimeException(sqlException);
      }
    }
    return result;
  }

  @Override
  public List<Order> getAllOrdersByUserId(String userId) {
    String sql = "select * from Orders where UserID=?";
    log.debug("sql: {}", sql);
    Connection connection = DbConnectionThreadLocal.getConnection();
    List<Order> result = new ArrayList<>();
    ResultSet rs = null;
    try (PreparedStatement psmt = connection.prepareStatement(sql);) {
      psmt.setString(1, userId);

      rs = psmt.executeQuery();
      while(rs.next()) {
        int orderId = rs.getInt("OrderID");
        int customerId = rs.getInt("CustomerID");
        LocalDateTime orderDate = rs.getTimestamp("OrderDate").toLocalDateTime();
        LocalDateTime shipDate = rs.getTimestamp("ShipDate").toLocalDateTime();
        String shipAddress = rs.getString("ShipAddress");

        Order order = new Order(orderId, customerId, orderDate, shipDate, userId, shipAddress);
        if(Objects.isNull(order)) {
          throw new RuntimeException("order is null");
        }
        result.add(order);
      }
      return result;
    }catch (SQLException sqlException) {
      throw new RuntimeException(sqlException);
    } finally {
      try {
        rs.close();
      } catch (SQLException sqlException) {
        throw new RuntimeException(sqlException);
      }
    }
  }
}
