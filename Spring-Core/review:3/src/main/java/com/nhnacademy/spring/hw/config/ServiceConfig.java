package com.nhnacademy.spring.hw.config;

import com.nhnacademy.spring.hw.sender.MessageSender;
import com.nhnacademy.spring.hw.sender.impl.DoorayMessageSender;
import com.nhnacademy.spring.hw.service.MessageSendService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.nhnacademy.spring.hw.service")
public class ServiceConfig {
}
