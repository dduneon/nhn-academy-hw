package com.nhnacademy.spring.hw.repository;

import com.nhnacademy.spring.hw.config.ParserConfig;
import com.nhnacademy.spring.hw.config.RepositoryConfig;
import com.nhnacademy.spring.hw.model.WaterTariff;
import com.nhnacademy.spring.hw.parser.DataParser;
import com.nhnacademy.spring.hw.repository.impl.DefaultTariffRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RepositoryConfig.class, ParserConfig.class})
public class TariffRepositoryTest {
  @InjectMocks
  private DefaultTariffRepository tariffRepository;

  @Mock
  private DataParser dataParser;

  static String testPath = "testPath";

  @BeforeEach
  void setup() {
    MockitoAnnotations.initMocks(this);

    List<WaterTariff> dataList = List.of(
        new WaterTariff(1, "City1", "Sector1", 0, 1, 10, 100, 0),
        new WaterTariff(2, "City2", "Sector2", 0, 11, 20, 200, 0),
        new WaterTariff(3, "City3", "Sector3", 0, 21, 30, 300, 0)
    );
    Mockito.when(dataParser.parse(testPath)).thenReturn(dataList);
  }
  @Test
  void loadFileTest() {
    tariffRepository.loadFile(testPath);
    Mockito.verify(dataParser, Mockito.atLeastOnce()).parse(testPath);
  }

  @Test
  void findPriceBasedOnUsageTest() {
    tariffRepository.loadFile(testPath);

    List<WaterTariff> executionWithDataResult = tariffRepository.findPriceBasedOnUsage(12);
    Assertions.assertEquals(1, executionWithDataResult.size());
    Assertions.assertEquals(2, executionWithDataResult.get(0).getSeq());
    Assertions.assertEquals("City2", executionWithDataResult.get(0).getCity());
    Assertions.assertEquals("Sector2", executionWithDataResult.get(0).getSector());
    Assertions.assertEquals(11, executionWithDataResult.get(0).getSectionStart());
    Assertions.assertEquals(20, executionWithDataResult.get(0).getSectionEnd());
    Assertions.assertEquals(200, executionWithDataResult.get(0).getUnitPrice());
  }

}
