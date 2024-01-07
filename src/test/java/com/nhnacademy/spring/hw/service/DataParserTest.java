package com.nhnacademy.spring.hw.service;

import com.nhnacademy.spring.hw.config.ParserConfig;
import com.nhnacademy.spring.hw.model.WaterTariff;
import com.nhnacademy.spring.hw.parser.DataParser;
import com.nhnacademy.spring.hw.parser.impl.CsvDataParser;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ParserConfig.class)
public class DataParserTest {
  @Autowired
  private DataParser dataParser;

  @BeforeEach
  void setup() {
  }

  @Test
  void parseTest() {
    List<WaterTariff> actual = dataParser.parse("/data/Tariff_20220331.csv");
    System.out.println(actual.size());
  }

}
