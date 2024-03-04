package com.nhnacademy.spring.hw.service;

import com.nhnacademy.spring.hw.model.WaterBill;
import com.nhnacademy.spring.hw.model.WaterTariff;
import com.nhnacademy.spring.hw.repository.TariffRepository;
import com.nhnacademy.spring.hw.service.impl.DefaultWaterUsagePriceService;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class WaterUsagePriceServiceTest {
  @InjectMocks
  DefaultWaterUsagePriceService defaultWaterUsagePriceService;

  @Mock
  TariffRepository tariffRepository;

  @BeforeEach
  void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void calculatePriceTest() {
    List<WaterTariff> testData = List.of(
        new WaterTariff(1, "City1", "Sector1", 0, 0, 0, 100, 0),
        new WaterTariff(2, "City2", "Sector2", 0, 0, 0, 200, 0),
        new WaterTariff(3, "City3", "Sector3", 0, 0, 0, 300, 0)
    );
    List<WaterBill> expected = List.of(
        new WaterBill("City1", "Sector1", 100, 100),
        new WaterBill("City2", "Sector2", 200, 200),
        new WaterBill("City3", "Sector3", 300, 300)
    );

    Mockito.when(tariffRepository.findPriceBasedOnUsage(1)).thenReturn(testData);
    List<WaterBill> actual = defaultWaterUsagePriceService.calculatePrice(1);

    for(int i=0; i<actual.size(); i++) {
      Assertions.assertEquals(expected.get(i).getCity(), actual.get(i).getCity());
      Assertions.assertEquals(expected.get(i).getSector(), actual.get(i).getSector());
      Assertions.assertEquals(expected.get(i).getUnitPrice(), actual.get(i).getUnitPrice());
      Assertions.assertEquals(expected.get(i).getBillTotal(), actual.get(i).getBillTotal());
    }
  }
}
