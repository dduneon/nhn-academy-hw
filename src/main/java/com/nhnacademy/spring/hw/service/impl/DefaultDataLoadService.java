package com.nhnacademy.spring.hw.service.impl;

import com.nhnacademy.spring.hw.repository.TariffRepository;
import com.nhnacademy.spring.hw.service.DataLoadService;
import org.springframework.stereotype.Service;

@Service
public class DefaultDataLoadService implements DataLoadService {
  private final TariffRepository tariffRepository;

  public DefaultDataLoadService(TariffRepository tariffRepository) {
    this.tariffRepository = tariffRepository;
  }

  @Override
  public void loadDataFromFile(String filePath) {
    tariffRepository.loadFile(filePath);
  }
}
