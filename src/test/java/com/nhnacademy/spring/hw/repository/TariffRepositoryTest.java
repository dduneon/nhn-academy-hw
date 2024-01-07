package com.nhnacademy.spring.hw.repository;

import com.nhnacademy.spring.hw.config.ParserConfig;
import com.nhnacademy.spring.hw.config.RepositoryConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RepositoryConfig.class, ParserConfig.class})
public class TariffRepositoryTest {
  private TariffRepository tariffRepository;

  @Test
  void findPriceBasedOnUsageTest() {

  }

}
