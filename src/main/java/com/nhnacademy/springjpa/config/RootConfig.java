package com.nhnacademy.springjpa.config;

import com.nhnacademy.springjpa.Base;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = Base.class)
public class RootConfig {
  @Bean
  public DataSource dataSource() {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
    dataSource.setUrl("jdbc:mysql://133.186.241.167:3306/nhn_academy_13?serverTimezone=UTC&characterEncoding=UTF-8");
    dataSource.setUsername("nhn_academy_13");
    dataSource.setPassword("EOcV(baaATcu3lny");

    dataSource.setInitialSize(10);
    dataSource.setMaxTotal(50);
    dataSource.setMinIdle(10);
    dataSource.setMaxIdle(30);

    dataSource.setMaxWaitMillis(5000);

    dataSource.setTestOnBorrow(true);
    dataSource.setTestOnReturn(true);
    dataSource.setTestWhileIdle(true);

    return dataSource;
  }
}
