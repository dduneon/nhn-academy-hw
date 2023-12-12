package com.nhnacademy.shoppingmall.PointRecords.service.Impl;

import com.nhnacademy.shoppingmall.PointRecords.domain.PointRecords;
import com.nhnacademy.shoppingmall.PointRecords.repository.PointRecordsRepository;
import com.nhnacademy.shoppingmall.PointRecords.service.PointRecordsService;
import com.nhnacademy.shoppingmall.order.domain.Order;
import java.util.List;
import java.util.Objects;

public class PointRecordsServiceImpl implements PointRecordsService {
  private final PointRecordsRepository pointRecordsRepository;

  public PointRecordsServiceImpl(PointRecordsRepository pointRecordsRepository) {
    this.pointRecordsRepository = pointRecordsRepository;
  }

  @Override
  public void saveRecord(PointRecords pointRecords) {
    if(Objects.isNull(pointRecords)) {
      throw new RuntimeException("pointRecords is null");
    }
    int result = pointRecordsRepository.save(pointRecords);
    if(result < 1) {
      throw new RuntimeException("save failed to PointRecords");
    }
  }

  @Override
  public List<PointRecords> getAllRecords(String userId) {
    return pointRecordsRepository.getAllUserRecords(userId);
  }
}
