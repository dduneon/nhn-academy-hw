package com.nhnacademy.springmvc.config;

import com.nhnacademy.springmvc.controller.ContollerBase;
import com.nhnacademy.springmvc.interceptor.AdminCheckInterceptor;
import com.nhnacademy.springmvc.interceptor.LoginCheckInterceptor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@EnableWebMvc
@Configuration
@ComponentScan(basePackageClasses = ContollerBase.class)
public class WebConfig implements WebMvcConfigurer, ApplicationContextAware {
  private ApplicationContext applicationContext;
  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }


  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LoginCheckInterceptor())
        .addPathPatterns("/**")
        .excludePathPatterns("/cs/login");
    registry.addInterceptor(new AdminCheckInterceptor())
        .addPathPatterns("/cs/admin/**");
  }

  @Override
  public void configureViewResolvers(ViewResolverRegistry registry) {
    registry.viewResolver(thymeleafViewResolver());
  }

  @Bean
  public ThymeleafViewResolver thymeleafViewResolver() {
    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
    viewResolver.setCharacterEncoding("UTF-8");
    viewResolver.setTemplateEngine(templateEngine());
    viewResolver.setOrder(1);
    viewResolver.setViewNames(new String[] {"*"});
    return viewResolver;
  }

  public SpringTemplateEngine templateEngine() {
    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    templateEngine.setTemplateResolver(templateResolver());
    // messageSource?
    return templateEngine;
  }

  public SpringResourceTemplateResolver templateResolver() {
    SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
    resolver.setApplicationContext(applicationContext);
    resolver.setCharacterEncoding("UTF-8");
    resolver.setPrefix("/WEB-INF/view/");
    resolver.setSuffix(".html");
    resolver.setTemplateMode("HTML5");
    return resolver;
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    //todo start page
    registry.addViewController("/").setViewName("main");
  }
}
