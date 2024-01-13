package com.nhnacademy.springmvc.config;

import com.nhnacademy.springmvc.Base;
import com.nhnacademy.springmvc.domain.Inquiry;
import com.nhnacademy.springmvc.domain.Role;
import com.nhnacademy.springmvc.domain.User;
import com.nhnacademy.springmvc.repository.InquiryRepository;
import com.nhnacademy.springmvc.repository.UserRepository;
import com.nhnacademy.springmvc.repository.impl.InquiryRepositoryImpl;
import com.nhnacademy.springmvc.repository.impl.UserRepositoryImpl;
import com.nhnacademy.springmvc.util.DateUtils;
import java.time.LocalDateTime;
import java.time.Month;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

@Slf4j
@Configuration
@ComponentScan(basePackageClasses = Base.class, excludeFilters = @ComponentScan.Filter(Controller.class))
public class RootConfig{

  @Bean
  public UserRepository userRepository() {
    UserRepository userRepository = new UserRepositoryImpl();
    userRepository.save(new User("user", "user", "사용자", Role.Customer));
    userRepository.save(new User("admin", "admin", "관리자", Role.Admin));
    return userRepository;
  }

  //todo test remove
  @Bean
  public InquiryRepository inquiryRepository() {
    InquiryRepository inquiryRepository = new InquiryRepositoryImpl();
    inquiryRepository.save(new Inquiry("user", "불만 있어요", "불만 접수", "comment", null, LocalDateTime.now(), false));
    inquiryRepository.save(new Inquiry("user", "제안합니다잉!", "제안", "comment", null, LocalDateTime.now().minusDays(1), false));
    inquiryRepository.save(new Inquiry("user", "사이즈 안맞아요!", "환불/교환", "comment", null, LocalDateTime.now().minusDays(2), false));
    inquiryRepository.save(new Inquiry("user", "칭찬칭찬", "칭찬해요", "comment", null, LocalDateTime.now().minusDays(3), false));
    inquiryRepository.save(new Inquiry("user", "기타문의에용", "기타 문의", "comment", null, LocalDateTime.now().minusDays(4), false));

    return inquiryRepository;
  }
}
