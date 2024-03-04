package com.nhnacademy.spring.hw.repository.impl;

import com.nhnacademy.spring.hw.model.WaterTariff;
import com.nhnacademy.spring.hw.repository.TariffRepository;
import java.util.Collections;
import com.nhnacademy.spring.hw.parser.DataParser;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultTariffRepository implements TariffRepository {
  private final DataParser dataParser;
  private List<WaterTariff> dataList;

  @Autowired
  public DefaultTariffRepository(DataParser dataParser) {
    this.dataParser = dataParser;
  }

  @Override
  public void loadFile(String filePath) {
    dataList = dataParser.parse(filePath);
  }

  @Override
  public List<WaterTariff> findPriceBasedOnUsage(int waterUsage) {
    if(Objects.isNull(dataList))
      return Collections.emptyList();
    return dataList.stream().filter(data -> data.isIncludeSection(waterUsage)).collect(Collectors.toList());
  }
}
