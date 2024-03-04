package com.nhnacademy.shoppingmall.PointRecords.service;

import com.nhnacademy.shoppingmall.PointRecords.domain.PointRecords;
import com.nhnacademy.shoppingmall.order.domain.Order;
import java.util.List;

public interface PointRecordsService {
  void saveRecord(PointRecords pointRecords);

  List<PointRecords> getAllRecords(String userId);
}
