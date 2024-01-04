package com.nhnacademy.spring.hw;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClassPathXmlMain {
  public static void main(String[] args) {

    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

  }
}