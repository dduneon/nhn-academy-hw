package com.nhnacademy.spring.hw.aspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;

public class CheckExecutionTimeAspect {
  private static final Log log = LogFactory.getLog(CheckExecutionTimeAspect.class);
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
      log.info(className + "." + methodName + " " + executionTime + "ms");
    }
  }

}
