package com.nhnacademy.springmvc.hw.config;

import com.nhnacademy.springmvc.hw.Base;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
@ComponentScan(basePackageClasses = Base.class, excludeFilters = { @ComponentScan.Filter(Controller.class)})
public class RootConfig {

}
