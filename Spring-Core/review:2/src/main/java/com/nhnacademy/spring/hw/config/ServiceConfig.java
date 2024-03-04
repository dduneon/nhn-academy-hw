package com.nhnacademy.spring.hw.config;

import com.nhnacademy.spring.hw.sender.MessageSender;
import com.nhnacademy.spring.hw.sender.impl.DoorayMessageSender;
import com.nhnacademy.spring.hw.service.MessageSendService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
  @Bean(name= "messageSendService")
  MessageSendService messageSendService(MessageSender doorayMessageSender) {
    return new MessageSendService(doorayMessageSender);
  }
}
