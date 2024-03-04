package com.nhnacademy.spring.hw.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "com.nhnacademy.spring.hw.aspect")
@EnableAspectJAutoProxy
public class AspectConfig {

}
