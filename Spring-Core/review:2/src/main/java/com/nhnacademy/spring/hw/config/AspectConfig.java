package com.nhnacademy.spring.hw.config;

import com.nhnacademy.spring.hw.aspect.CheckExecutionTimeAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AspectConfig {
  @Bean(name = "checkExecutionTimeAspect")
  CheckExecutionTimeAspect checkExecutionTimeAspect() {
    return new CheckExecutionTimeAspect();
  }
}
