package com.nhnacademy.edu.springframework.project.aspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class CheckExecutionTimeAspect {
  private static Log log = LogFactory.getLog(CheckExecutionTimeAspect.class);

  @Around("execution(* com.nhnacademy.edu.springframework.project.service.*.*(..))")
  public Object doChecking(ProceedingJoinPoint pjp) throws Throwable {
    StopWatch stopWatch = new StopWatch();
    String targetClassName = pjp.getTarget().getClass().getSimpleName();
    String targetMethodName = pjp.getSignature().getName();
    try {
      stopWatch.start();

      return pjp.proceed();
    } finally {
      stopWatch.stop();
      long executionTime = stopWatch.getTotalTimeMillis();
      log.info(targetClassName + "." + targetMethodName + " " + executionTime + "ms");
    }
  }

}
