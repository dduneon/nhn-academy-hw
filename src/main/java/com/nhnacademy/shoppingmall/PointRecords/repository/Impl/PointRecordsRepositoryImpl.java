package com.nhnacademy.shoppingmall.PointRecords.repository.Impl;

import com.nhnacademy.shoppingmall.PointRecords.domain.PointRecords;
import com.nhnacademy.shoppingmall.PointRecords.repository.PointRecordsRepository;
import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.order.domain.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PointRecordsRepositoryImpl implements PointRecordsRepository {

  @Override
  public int save(PointRecords pointRecords) {
    String sql = "insert into PointRecords values(?,?,?,?)";
    Connection connection = DbConnectionThreadLocal.getConnection();

    try (PreparedStatement psmt = connection.prepareStatement(sql)){
      psmt.setString(1, pointRecords.getUserId());
      psmt.setInt(2, pointRecords.getAmount());
      Integer orderId = pointRecords.getOrderId().orElse(null);

      if(Objects.nonNull(orderId)) {
        psmt.setInt(3, orderId);
      }
      psmt.setTimestamp(4, Timestamp.valueOf(pointRecords.getRecordDate()));
      //todo null잘 들어가는지 확인할 것
      int result = psmt.executeUpdate();
      log.debug("result: {}", result);
      return result;
    }catch (SQLException sqlException) {
      throw new RuntimeException(sqlException);
    }
  }

  @Override
  public List<PointRecords> getAllUserRecords(String userId) {
    String sql = "select * from PointRecords where user_id=?";
    log.debug("sql: {}", sql);
    Connection connection = DbConnectionThreadLocal.getConnection();
    List<PointRecords> result = new ArrayList<>();
    ResultSet rs = null;
    try (PreparedStatement psmt = connection.prepareStatement(sql);) {
      psmt.setString(1, userId);

      rs = psmt.executeQuery();
      while(rs.next()) {
        int amount = rs.getInt("Amount");
        Integer orderId = null;
        if(amount < 0) {
          orderId = rs.getInt("OrderID");
        }
        LocalDateTime recordDate = rs.getTimestamp("RecordDate").toLocalDateTime();
        PointRecords pointRecords = new PointRecords(userId, amount, orderId, recordDate);
        if(Objects.isNull(pointRecords)) {
          throw new RuntimeException("record is null");
        }
        result.add(pointRecords);
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
