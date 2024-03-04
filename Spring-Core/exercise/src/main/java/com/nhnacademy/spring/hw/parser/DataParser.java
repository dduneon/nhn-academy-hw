package com.nhnacademy.spring.hw.parser;

import com.nhnacademy.spring.hw.model.WaterTariff;
import java.util.List;

public interface DataParser {
  List<WaterTariff> parse(String filePath);
}
