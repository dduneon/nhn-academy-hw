package com.nhnacademy.springmvc.hw;

import com.nhnacademy.springmvc.hw.config.RootConfig;
import com.nhnacademy.springmvc.hw.config.WebConfig;
import javax.servlet.Filter;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Order(2)
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class[] {RootConfig.class};
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class[] {WebConfig.class};
  }

  @Override
  protected String[] getServletMappings() {
    return new String[] { "/" };
  }
}
