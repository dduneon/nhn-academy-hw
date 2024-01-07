package com.nhnacademy.spring.hw.service.impl;

import com.nhnacademy.spring.hw.model.WaterBill;
import com.nhnacademy.spring.hw.model.WaterTariff;
import com.nhnacademy.spring.hw.repository.TariffRepository;
import com.nhnacademy.spring.hw.repository.impl.DefaultTariffRepository;
import com.nhnacademy.spring.hw.service.WaterUsagePriceService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class DefaultWaterUsagePriceService implements WaterUsagePriceService {
  private final TariffRepository tariffRepository;

  public DefaultWaterUsagePriceService(TariffRepository tariffRepository) {
    this.tariffRepository = tariffRepository;
  }

  @Override
  public List<WaterBill> calculatePrice(int waterUsage) {
    List<WaterTariff> filteredTariffList = tariffRepository.findPriceBasedOnUsage(waterUsage);
    List<WaterBill> calculatedBillList = filteredTariffList.stream()
        .map(tariff -> new WaterBill(tariff.getCity(), tariff.getSector(), tariff.getUnitPrice(), Math.multiplyExact(waterUsage, tariff.getUnitPrice())))
        .collect(Collectors.toList());

    return calculatedBillList;
  }
}
