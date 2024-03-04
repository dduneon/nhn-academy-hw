package com.nhnacademy.spring.hw.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "com.nhnacademy.spring.hw.parser")
@PropertySource("classpath:config/filepath.properties")
public class ParserConfig {


}
