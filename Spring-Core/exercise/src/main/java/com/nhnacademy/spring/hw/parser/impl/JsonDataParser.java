package com.nhnacademy.spring.hw.parser.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.spring.hw.condition.JsonFormatCondition;
import com.nhnacademy.spring.hw.model.WaterTariff;
import com.nhnacademy.spring.hw.parser.DataParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@Conditional(JsonFormatCondition.class)
public class JsonDataParser implements DataParser {

  @Override
  public List<WaterTariff> parse(String filePath) {
    ObjectMapper objectMapper = new ObjectMapper();
    try (
        InputStream jsonInputStream = getClass().getClassLoader().getResourceAsStream(filePath);
    ) {
      List<WaterTariff> parsedDataList = objectMapper.readValue(jsonInputStream, new TypeReference<List<WaterTariff>>() {});
      return parsedDataList;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
