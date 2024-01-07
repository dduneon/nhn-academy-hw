package com.nhnacademy.spring.hw.service;

import com.nhnacademy.spring.hw.repository.TariffRepository;
import com.nhnacademy.spring.hw.service.impl.DefaultDataLoadService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class DataLoadServiceTest {
  @InjectMocks
  private DefaultDataLoadService dataLoadService;

  @Mock
  private TariffRepository tariffRepository;

  @BeforeEach
  void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void loadDataFromFileTest() {
    String testFilePath = "testFilePath";
    dataLoadService.loadDataFromFile(testFilePath);

    Mockito.verify(tariffRepository, Mockito.atLeastOnce()).loadFile(testFilePath);
  }

  @Test
  void loadFilePathFromPropertiesTest() {
    String expectedFilePath = "expectedFilePath";
    Mockito.when(dataLoadService.loadFilePathFromProperties()).thenReturn(expectedFilePath);

    String resultFilePath = dataLoadService.loadFilePathFromProperties();
    Assertions.assertEquals(expectedFilePath, resultFilePath);
  }

}
