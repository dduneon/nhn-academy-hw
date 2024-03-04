package com.nhnacademy.spring.hw.parser.impl;

import com.nhnacademy.spring.hw.condition.CsvFormatCondition;
import com.nhnacademy.spring.hw.model.WaterTariff;
import com.nhnacademy.spring.hw.parser.DataParser;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@Conditional(CsvFormatCondition.class)
public class CsvDataParser implements DataParser {

  @Override
  public List<WaterTariff> parse(String filePath) {
    List<WaterTariff> parsedDataList = new ArrayList<>();
    try (
        InputStream csvInputStream = getClass().getClassLoader().getResourceAsStream(filePath);
        BufferedReader br = new BufferedReader(new InputStreamReader(csvInputStream));
    ) {
      br.readLine();
      String readData = br.readLine();
      while(readData != null) {
        String[] splitData = readData.split(",");
        WaterTariff data = new WaterTariff(
            Integer.parseInt(splitData[0].trim()),
            splitData[1].trim(),
            splitData[2].trim(),
            Integer.parseInt(splitData[3].trim()),
            Integer.parseInt(splitData[4].trim()),
            Integer.parseInt(splitData[5].trim()),
            Integer.parseInt(splitData[6].trim()),
            (splitData.length == 7) || splitData[7].trim().isEmpty() ? 0 : Integer.parseInt(splitData[7].trim())
        );
        parsedDataList.add(data);
        readData = br.readLine();
      }
    } catch (IOException ioException) {
      throw new RuntimeException(ioException);
    }
    return parsedDataList;
  }


}
