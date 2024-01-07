package com.nhnacademy.spring.hw.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.nhnacademy.spring.hw.service")
public class ServiceConfig {

}
