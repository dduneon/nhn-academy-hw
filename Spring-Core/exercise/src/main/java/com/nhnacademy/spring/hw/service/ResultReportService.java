package com.nhnacademy.spring.hw.service;

import com.nhnacademy.spring.hw.model.WaterBill;
import java.util.List;

public interface ResultReportService {
  void reportResult(List<WaterBill> data);
}
