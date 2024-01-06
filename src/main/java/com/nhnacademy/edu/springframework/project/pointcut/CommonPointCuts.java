package com.nhnacademy.edu.springframework.project.pointcut;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointCuts {
  @Pointcut("within(com.nhnacademy.edu.springframework.project.service.impl.*)")
  public void inServiceLayer() {}

}
