package com.nhnacademy.spring.hw.repository;

import com.nhnacademy.spring.hw.model.WaterTariff;
import java.util.List;

public interface TariffRepository {
  void loadFile(String filePath);
  List<WaterTariff> findPriceBasedOnUsage(int waterUsage);
}
