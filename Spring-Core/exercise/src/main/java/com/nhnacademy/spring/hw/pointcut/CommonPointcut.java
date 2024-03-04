package com.nhnacademy.spring.hw.pointcut;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointcut {

  @Pointcut("within(*)")
  public void onAllMethod() {}

}
