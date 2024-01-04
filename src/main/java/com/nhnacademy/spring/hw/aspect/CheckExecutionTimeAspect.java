package com.nhnacademy.spring.hw.aspect;

import org.springframework.util.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;

public class CheckExecutionTimeAspect {
  public Object doChecking(ProceedingJoinPoint pjp) throws Throwable{
    StopWatch stopWatch = new StopWatch();
    String className = pjp.getTarget().getClass().getSimpleName();
    String methodName = pjp.getSignature().getName();

    try {
      stopWatch.start();

      return pjp.proceed();
    } finally {
      stopWatch.stop();
      long executionTime = stopWatch.getTotalTimeMillis();
      System.out.println(className + "." + methodName + " " + executionTime + "ms");
    }
  }

}
