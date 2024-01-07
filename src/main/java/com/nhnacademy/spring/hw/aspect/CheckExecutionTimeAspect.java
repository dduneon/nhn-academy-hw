package com.nhnacademy.spring.hw.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class CheckExecutionTimeAspect {
  private static final Logger log = LoggerFactory.getLogger(CheckExecutionTimeAspect.class);
  @Around("com.nhnacademy.spring.hw.pointcut.CommonPointcut.onAllMethod()")
  public Object doCheck(ProceedingJoinPoint pjp) throws Throwable{
    StopWatch stopWatch = new StopWatch();
    String targetClassName = pjp.getTarget().getClass().getSimpleName();
    String targetMethodName = pjp.getSignature().getName();

    try {
      stopWatch.start();

      return pjp.proceed();
    } finally {
      stopWatch.stop();
      long executionTime = stopWatch.getTotalTimeMillis();

      String message = targetClassName + "." + targetMethodName + " " + executionTime + "ms";
      log.info(message);
    }
  }
}
