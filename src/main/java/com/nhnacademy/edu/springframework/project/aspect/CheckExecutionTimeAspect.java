package com.nhnacademy.edu.springframework.project.aspect;

import java.util.Objects;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class CheckExecutionTimeAspect {
  public Object doChecking(ProceedingJoinPoint pjp) throws Throwable {
    StopWatch stopWatch = new StopWatch();
    String targetClassName = pjp.getTarget().getClass().getSimpleName();
    String targetMethodName = pjp.getSignature().getName();
    try {
      stopWatch.start();

      return pjp.proceed();
    } finally {
      stopWatch.stop();

    }
  }

}
