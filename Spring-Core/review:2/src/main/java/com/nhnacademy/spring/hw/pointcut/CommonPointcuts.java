package com.nhnacademy.spring.hw.pointcut;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointcuts {
  @Pointcut("within(com.nhnacademy.spring.hw.sender.impl.DoorayMessageSender)")
  public void inDoorayMessageSender() {}

  @Pointcut("@annotation(com.nhnacademy.spring.hw.annotation.CheckExecutionTime)")
  public void hasCheckExecutionTimeAnnotation() {}
}
