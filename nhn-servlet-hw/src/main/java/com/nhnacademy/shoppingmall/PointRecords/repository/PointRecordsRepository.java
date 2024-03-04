package com.nhnacademy.shoppingmall.PointRecords.repository;

import com.nhnacademy.shoppingmall.PointRecords.domain.PointRecords;
import com.nhnacademy.shoppingmall.order.domain.Order;
import java.util.List;

public interface PointRecordsRepository {

  int save(PointRecords pointRecords);

  List<PointRecords> getAllUserRecords(String userId);
}
