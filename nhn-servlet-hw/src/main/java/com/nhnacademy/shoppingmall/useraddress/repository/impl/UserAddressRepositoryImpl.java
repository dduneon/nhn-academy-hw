package com.nhnacademy.shoppingmall.useraddress.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.useraddress.repository.UserAddressRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserAddressRepositoryImpl implements UserAddressRepository {

  @Override
  public List<String> getUserAddresses(String userId) {
    Connection connection = DbConnectionThreadLocal.getConnection();
    String sql = "select user_address from UserAddresses where user_id=?";
    log.debug("sql:{}", sql);

    List<String> addressList = new ArrayList<>();
    ResultSet rs = null;
    try (PreparedStatement psmt = connection.prepareStatement(sql);) {
      psmt.setString(1, userId);

      rs = psmt.executeQuery();
      while (rs.next()) {
        addressList.add(rs.getString(1));
      }
      log.debug("Address list size: {}", addressList.size());
      return addressList;
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
