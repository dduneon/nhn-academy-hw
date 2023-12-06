package com.nhnacademy.shoppingmall.product.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.ProductRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j

public class ProductRepositoryImpl implements ProductRepository {

  /**
   * @param productId
   * @return
   */
  @Override
  public Optional<Product> findByProductId(int productId) {
    Connection connection = DbConnectionThreadLocal.getConnection();
    String sql = "select * from Products where ProductID=?";
    log.debug("sql:{}", sql);

    ResultSet rs = null;
    try (PreparedStatement psmt = connection.prepareStatement(sql);
    ) {
      psmt.setInt(1, productId);
      rs = psmt.executeQuery();
      if (rs.next()) {
        Product product = new Product(
            rs.getInt("ProductId"),
            rs.getString("ModelNumber"),
            rs.getString("ModelName"),
            rs.getString("ProductImage"),
            rs.getInt("UnitCost"),
            rs.getString("Description")
        );
        return Optional.of(product);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      try {
        rs.close();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }

    return Optional.empty();
  }

  /**
   * @return
   */
  @Override
  public List<Product> getAllProductList() {
    return getLimitedList(0, Integer.MAX_VALUE);
  }

  /**
   * @param
   * @return
   */
  @Override
  public int getProductCount() {
    Connection connection = DbConnectionThreadLocal.getConnection();
    String sql = "select count(*) from Products";

    ResultSet rs = null;
    try (PreparedStatement psmt = connection.prepareStatement(sql);
    ) {
      rs = psmt.executeQuery();
      if (rs.next()) {
        return rs.getInt(1);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      try {
        rs.close();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
    return 0;
  }

  /**
   * @param product
   * @return
   */
  @Override
  public int save(Product product) {
    Connection connection = DbConnectionThreadLocal.getConnection();
    String sql = "insert into Products values(?,?,?,?,?,?)";
    log.debug("sql:{}", sql);

    try (PreparedStatement psmt = connection.prepareStatement(sql);) {
      psmt.setInt(1, product.getProductId());
      psmt.setString(2, product.getModelNumber());
      psmt.setString(3, product.getModelName());
      psmt.setString(4, product.getProductImage());
      psmt.setInt(5, product.getUnitCost());
      psmt.setString(6, product.getDescription());

      int result = psmt.executeUpdate();
      log.debug("result: {}", result);
      return result;
    } catch (SQLException sqlException) {
      throw new RuntimeException(sqlException);
    }
  }

  /**
   * @param productId
   * @return
   */
  @Override
  public int deleteByProductId(int productId) {
    Connection connection = DbConnectionThreadLocal.getConnection();
    String sql = "delete from Products where ProductId=?";
    log.debug("sql:{}", sql);

    try (PreparedStatement psmt = connection.prepareStatement(sql);) {
      psmt.setInt(1, productId);

      int result = psmt.executeUpdate();
      log.debug("result: {}", result);
      return result;
    } catch (SQLException sqlException) {
      throw new RuntimeException(sqlException);
    }
  }

  /**
   * @param product
   * @return
   */
  @Override
  public int update(Product product) {
    Connection connection = DbConnectionThreadLocal.getConnection();
    String sql = "update Products set ModelNumber=?, ModelName=?, ProductImage=?, UnitCost=?, Description=? where ProductID=?";
    log.debug("sql:{}", sql);

    try (PreparedStatement psmt = connection.prepareStatement(sql);) {
      psmt.setString(1, product.getModelName());
      psmt.setString(2, product.getModelName());
      psmt.setString(3, product.getProductImage());
      psmt.setInt(4, product.getUnitCost());
      psmt.setString(5, product.getDescription());
      psmt.setInt(6, product.getProductId());

      int result = psmt.executeUpdate();
      log.debug("result: {}", result);
      return result;
    } catch (SQLException sqlException) {
      throw new RuntimeException(sqlException);
    }
  }

  /**
   * @param offset
   * @param row_count
   * @return
   */
  @Override
  public List<Product> getLimitedList(long offset, long row_count) {
    Connection connection = DbConnectionThreadLocal.getConnection();
    String sql = "select * from Products order by ProductID asc limit ?,?";

    log.debug("sql: {}, offset: {}, row_count: {}", sql, offset, row_count);
    List<Product> result = new ArrayList<>();
    ResultSet rs = null;
    try (PreparedStatement psmt = connection.prepareStatement(sql);
    ) {
      psmt.setLong(1, offset);
      psmt.setLong(2, row_count);

      rs = psmt.executeQuery();
      while (rs.next()) {
        Product product = new Product(
            rs.getInt("ProductId"),
            rs.getString("ModelNumber"),
            rs.getString("ModelName"),
            rs.getString("ProductImage"),
            rs.getInt("UnitCost"),
            rs.getString("Description")
        );
        log.debug("product({}) added", product.getProductId());
        result.add(product);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      try {
        rs.close();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
    log.debug("result size is: ", result.size());
    return result;
  }
}
