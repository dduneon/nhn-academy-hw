package com.nhnacademy.spring.hw.parser;

import com.nhnacademy.spring.hw.model.WaterTariff;
import com.nhnacademy.spring.hw.parser.impl.CsvDataParser;
import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class CsvDataParserTest {
  private DataParser dataParser;

  @BeforeEach
  void setup(){
    dataParser = new CsvDataParser();
  }

  @Test
  void parseThrowExceptionTest() {
    String notExistPath = "notExistPath";
    Assertions.assertThrows(RuntimeException.class, () -> dataParser.parse(notExistPath));
  }

  @Test
  void parseTest() {
    List<WaterTariff> expectedList = List.of(
        new WaterTariff(1, "동두천시", "가정용", 1, 1, 20, 690, 0),
        new WaterTariff(2, "동두천시", "가정용", 2, 21, 30, 1090, 0),
        new WaterTariff(3, "동두천시", "가정용", 3, 31, 999999, 1530, 0),
        new WaterTariff(4, "동두천시", "일반용", 1, 1, 100, 1410, 0),
        new WaterTariff(5, "동두천시", "일반용", 2, 101, 300, 1480, 0)
    );

    List<WaterTariff> returnedList = dataParser.parse("data/test.csv");

    for(int i=0; i<expectedList.size(); i++) {
      WaterTariff expected = expectedList.get(i);
      WaterTariff actual = returnedList.get(i);
      Assertions.assertEquals(expected.getSeq(), actual.getSeq());
      Assertions.assertEquals(expected.getLevel(), actual.getLevel());
      Assertions.assertEquals(expected.getUnitPrice(), actual.getUnitPrice());
    }
  }

}
