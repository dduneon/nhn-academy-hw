package com.nhnacademy.shoppingmall.cart.repository.impl;

import com.nhnacademy.shoppingmall.cart.domain.Cart;
import com.nhnacademy.shoppingmall.cart.repository.CartRepository;
import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CartRepositoryImpl implements CartRepository {

  /**
   * @param productId
   * @param userId
   * @return
   */
  @Override
  public int save(int productId, String userId) {
    String sql = "insert into ShoppingCart(Quantity, ProductID, user_id) values(?, ?, ?)";
    Connection connection = DbConnectionThreadLocal.getConnection();
    try (PreparedStatement statement = connection.prepareStatement(sql);) {
      statement.setInt(1, 1);
      statement.setInt(2, productId);
      statement.setString(3, userId);

      int result = statement.executeUpdate();
      log.debug("result: {}", result);
      return result;
    } catch (SQLException sqlException) {
      throw new RuntimeException(sqlException);
    }
  }

  /**
   * @param productId
   * @param userId
   * @return
   */
  @Override
  public int delete(int productId, String userId) {
    String sql = "delete from ShoppingCart where productId=? and user_id=?";
    Connection connection = DbConnectionThreadLocal.getConnection();
    try (PreparedStatement statement = connection.prepareStatement(sql);) {
      statement.setInt(1, productId);
      statement.setString(2, userId);

      int result = statement.executeUpdate();
      log.debug("result: {}", result);
      return result;
    } catch (SQLException sqlException) {
      throw new RuntimeException(sqlException);
    }
  }

  /**
   * @param quantity
   * @param productId
   * @param userId
   * @return
   */
  @Override
  public int update(int quantity, int productId, String userId) {
    String sql = "update ShoppingCart set Quantity=? where productId=? and userId=?";

    Connection connection = DbConnectionThreadLocal.getConnection();
    try (PreparedStatement statement = connection.prepareStatement(sql);) {
      statement.setInt(1, quantity);
      statement.setInt(2, productId);
      statement.setString(3, userId);

      int result = statement.executeUpdate();
      log.debug("result: {}", result);
      return result;
    } catch (SQLException sqlException) {
      throw new RuntimeException(sqlException);
    }
  }

  /**
   * @param userId
   * @return
   */
  @Override
  public List<Cart> getCart(String userId) {
    String sql = "select * from ShoppingCart where user_id=?";

    List<Cart> result = new ArrayList<>();
    ResultSet rs = null;
    Connection connection = DbConnectionThreadLocal.getConnection();
    try (PreparedStatement statement = connection.prepareStatement(sql);) {
      statement.setString(1, userId);

      rs = statement.executeQuery();
      while (rs.next()) {
        int temp_recordId = rs.getInt("RecordID");
        int temp_quantity = rs.getInt("Quantity");
        int temp_productId = rs.getInt("ProductID");
        LocalDateTime temp_dateCreated = rs.getTimestamp("DateCreateed").toLocalDateTime();
        String temp_userId = rs.getString("user_id");

        Cart cart = new Cart(temp_recordId, temp_quantity, temp_productId, temp_dateCreated,
            temp_userId);
        if (Objects.nonNull(cart)) {
          result.add(cart);
        }
      }
      return result;
    } catch (SQLException sqlException) {
      throw new RuntimeException(sqlException);
    }
  }
}
