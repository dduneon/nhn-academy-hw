package com.nhnacademy.spring.hw.config;

import com.nhn.dooray.client.DoorayHookSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource("classpath:api-info.properties")
@ComponentScan(basePackages = "com.nhnacademy.spring.hw.sender.impl")
public class SenderConfig {
  @Bean(name="restTemplate")
  RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean(name="doorayHookSender")
  DoorayHookSender doorayHookSender(RestTemplate restTemplate, @Value("${hookUrl}") String hookUrl) {
    return new DoorayHookSender(restTemplate, hookUrl);
  }
}
