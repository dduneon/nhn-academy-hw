package com.nhnacademy.shoppingmall.cart.repository.impl;

import com.nhnacademy.shoppingmall.cart.repository.CartRepository;
import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.join.domain.ProductInfoInCart;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
    log.debug("sql:{}", sql);
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
    log.debug("sql:{}", sql);
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
    log.debug("sql:{}", sql);

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
  public List<ProductInfoInCart> getProductInfoInCart(String userId) {
    String sql = "select p.ProductID, p.ProductImage, p.ModelName, p.UnitCost, s.Quantity, s.DateCreated, s.user_id from ShoppingCart as s join Products as p on s.ProductID = p.ProductID where user_id=?";
    log.debug("sql:{}", sql);

    List<ProductInfoInCart> result = new ArrayList<>();
    ResultSet rs = null;
    Connection connection = DbConnectionThreadLocal.getConnection();
    try (PreparedStatement statement = connection.prepareStatement(sql);) {
      statement.setString(1, userId);

      rs = statement.executeQuery();
      while (rs.next()) {
        int temp_productId = rs.getInt("p.ProductID");
        String temp_productImage = rs.getString("p.ProductImage");
        String temp_modelName = rs.getString("p.ModelName");
        int temp_unitCost = rs.getInt("p.UnitCost");
        int temp_quantity = rs.getInt("s.Quantity");
        LocalDateTime temp_dateCreated = rs.getTimestamp("s.DateCreated").toLocalDateTime();
        String temp_userId = rs.getString("user_id");

        result.add(
            new ProductInfoInCart(temp_productId, temp_productImage, temp_modelName, temp_unitCost,
                temp_quantity, temp_dateCreated, temp_userId));

        log.debug("ProductInfoInChart added: {}", temp_productId);
      }
      return result;
    } catch (SQLException sqlException) {
      throw new RuntimeException(sqlException);
    } finally {
      try {
        rs.close();
      } catch (SQLException sqlException) {
        throw new RuntimeException(sqlException);
      }
    }
  }

  @Override
  public int countByProductIdUserId(int productId, String userId) {
    //todo#3-7 userId와 일치하는 회원의 count를 반환합니다.
    Connection connection = DbConnectionThreadLocal.getConnection();
    String sql = "select count(*) from ShoppingCart where ProductId=? and user_id=?";
    log.debug("sql:{}", sql);

    ResultSet rs = null;
    try (PreparedStatement psmt = connection.prepareStatement(sql);) {
      psmt.setInt(1, productId);
      psmt.setString(2, userId);

      rs = psmt.executeQuery();
      int result = 0;
      if (rs.next()) {
        result = rs.getInt(1);
      }
      log.debug("result: {}", result);
      return result;
    } catch (SQLException sqlException) {
      throw new RuntimeException(sqlException);
    } finally {
      try {
        rs.close();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
