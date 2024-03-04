package com.nhnacademy.shoppingmall.categoryproduct.repository.impl;

import com.nhnacademy.shoppingmall.categoryproduct.repository.CategoryProductRepository;
import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CategoryProductRepositoryImpl implements CategoryProductRepository{

  @Override
  public List<String> getCategoriesName(int productId) {
    String sql = "select c.CategoryName from Categories as c join CategoryProduct as cp on c.CategoryID = cp.CategoryID where cp.ProductID=?";
    Connection connection = DbConnectionThreadLocal.getConnection();

    List<String> result = new ArrayList<>();
    ResultSet rs = null;
    try (PreparedStatement psmt = connection.prepareStatement(sql);) {
      psmt.setInt(1, productId);

      rs = psmt.executeQuery();
      while(rs.next()) {
        result.add(rs.getString(1));
      }
      log.debug("result size: {}", result.size());
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
}
