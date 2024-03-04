package com.nhnacademy.springmvc.hw.config;

import com.nhnacademy.springmvc.hw.Base;
import com.nhnacademy.springmvc.hw.repository.Impl.StudentRepositoryImpl;
import com.nhnacademy.springmvc.hw.repository.StudentRepository;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackageClasses = Base.class, excludeFilters = { @ComponentScan.Filter(Controller.class)})
public class RootConfig {

  @Bean
  public StudentRepository studentRepository() {
    StudentRepository studentRepository = new StudentRepositoryImpl();
    studentRepository.register("테스트", "test@nhnacademy.com", 100, "훌륭한 학생");

    return studentRepository;
  }

  @Bean
  public MessageSource messageSource() {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    messageSource.setDefaultEncoding("UTF-8");
    messageSource.setBasename("message");

    return messageSource;
  }
}
