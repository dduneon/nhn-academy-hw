package com.nhnacademy.spring.hw.service.impl;

import com.nhnacademy.spring.hw.model.WaterBill;
import com.nhnacademy.spring.hw.service.ResultReportService;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultResultReportService implements ResultReportService {
  @Override
  public void reportResult(List<WaterBill> data) {
    List<WaterBill> result = data.stream().sorted(Comparator.comparingInt(WaterBill::getBillTotal)).limit(5).collect(
        Collectors.toList());
    for(WaterBill bill: result) {
      System.out.println(bill);
    }
  }
}
