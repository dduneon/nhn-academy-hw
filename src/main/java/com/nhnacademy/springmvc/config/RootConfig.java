package com.nhnacademy.springmvc.config;

import com.nhnacademy.springmvc.Base;
import com.nhnacademy.springmvc.domain.Role;
import com.nhnacademy.springmvc.domain.User;
import com.nhnacademy.springmvc.repository.UserRepository;
import com.nhnacademy.springmvc.repository.impl.UserRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Slf4j
@Configuration
@ComponentScan(basePackageClasses = Base.class, excludeFilters = @ComponentScan.Filter(Controller.class))
public class RootConfig{

  @Bean
  public UserRepository userRepository() {
    UserRepository userRepository = new UserRepositoryImpl();
    userRepository.save(new User("test", "test", "김준현", Role.User));
    log.debug("userRepository: test user exist: {}", userRepository.isExist("test"));
    return userRepository;
  }
}
