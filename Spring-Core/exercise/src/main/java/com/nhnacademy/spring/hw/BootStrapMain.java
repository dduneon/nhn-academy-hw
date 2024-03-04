package com.nhnacademy.spring.hw;

import com.nhnacademy.spring.hw.model.WaterBill;
import com.nhnacademy.spring.hw.service.DataLoadService;
import com.nhnacademy.spring.hw.service.ResultReportService;
import com.nhnacademy.spring.hw.service.WaterUsagePriceService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BootStrapMain {

  public static void main(String[] args) throws IOException {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.nhnacademy.spring.hw.config");
    DataLoadService dataLoadService = context.getBean("defaultDataLoadService", DataLoadService.class);
    String filePath = dataLoadService.loadFilePathFromProperties();
    dataLoadService.loadDataFromFile(filePath);

    WaterUsagePriceService waterUsagePriceService = context.getBean("defaultWaterUsagePriceService", WaterUsagePriceService.class);
    ResultReportService resultReportService = context.getBean("defaultResultReportService", ResultReportService.class);

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    while(true) {
      System.out.print("Input Water Usage (exit when input nothing) > ");
      String userInput = br.readLine();

      if(Objects.isNull(userInput) || userInput.isEmpty()) {
        break;
      }

      try {
        int userInputWaterUsage = Integer.parseInt(userInput);
        List<WaterBill> calculatePriceList = waterUsagePriceService.calculatePrice(userInputWaterUsage);
        resultReportService.reportResult(calculatePriceList);
      } catch (NumberFormatException npe) {
        System.out.println("must enter number(integer)");
      }
    }
  }

}
