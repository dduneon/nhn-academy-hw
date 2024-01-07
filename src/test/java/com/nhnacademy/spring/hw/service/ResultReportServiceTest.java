package com.nhnacademy.spring.hw.service;

import com.nhnacademy.spring.hw.model.WaterBill;
import com.nhnacademy.spring.hw.service.impl.DefaultResultReportService;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ResultReportServiceTest {
  private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  @BeforeEach
  public void setUpStreams() {
    System.setOut(new PrintStream(outputStream));
  }

  @AfterEach
  public void restoreStreams() {
    System.setOut(originalOut);
  }

  @Test
  public void testReportResult() {
    List<WaterBill> testData = List.of(
        new WaterBill("City1", "Sector1", 100, 10000),
        new WaterBill("City2", "Sector2", 200, 20000),
        new WaterBill("City3", "Sector3", 300, 30000),
        new WaterBill("City4", "Sector4", 400, 40000),
        new WaterBill("City5", "Sector5", 500, 50000),
        new WaterBill("City6", "Sector6", 600, 60000)
    );

    ResultReportService resultReportService = new DefaultResultReportService();

    captureSystemOut(() -> {
      resultReportService.reportResult(testData);
    });

    String expectedOutput = "WaterBill{city='City1', sector='Sector1', unitPrice=100, billTotal=10000}\n"
        + "WaterBill{city='City2', sector='Sector2', unitPrice=200, billTotal=20000}\n"
        + "WaterBill{city='City3', sector='Sector3', unitPrice=300, billTotal=30000}\n"
        + "WaterBill{city='City4', sector='Sector4', unitPrice=400, billTotal=40000}\n"
        + "WaterBill{city='City5', sector='Sector5', unitPrice=500, billTotal=50000}\n";


    Assertions.assertEquals(expectedOutput, outputStream.toString());
  }

  private void captureSystemOut(Runnable runnable) {
    runnable.run();
  }
}
