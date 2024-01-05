package com.nhnacademy.spring.hw.config;

import com.nhn.dooray.client.DoorayHookSender;
import com.nhnacademy.spring.hw.sender.MessageSender;
import com.nhnacademy.spring.hw.sender.impl.DoorayMessageSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SenderConfig {
  @Bean(name="restTemplate")
  RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean(name="doorayHookSender")
  DoorayHookSender doorayHookSender(RestTemplate restTemplate, @Value("hookUrl") String hookUrl) {
    return new DoorayHookSender(restTemplate, hookUrl);
  }

  @Bean(name = "doorayMessageSender")
  MessageSender doorayMessageSender(DoorayHookSender doorayHookSender) {
    return new DoorayMessageSender(doorayHookSender);
  }
}
