package com.nhnacademy.spring.hw.repository.impl;

import com.nhnacademy.spring.hw.model.WaterBill;
import com.nhnacademy.spring.hw.repository.BillRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultBillRepository implements BillRepository {
  private List<WaterBill> billList;

  public DefaultBillRepository(List<WaterBill> billList) {
    this.billList = billList;
  }
}
