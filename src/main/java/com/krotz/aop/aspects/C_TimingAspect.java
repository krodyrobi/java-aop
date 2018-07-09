package com.krotz.aop.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class C_TimingAspect {
  @Pointcut("execution(* com.krotz.aop.C_SampleWithAop.*(..))")
  public void targetClassMethods() {}

  //    @annotation is already a pointcut can combine more granular pointcuts
  //    @Around("@annotation(com.krotz.aop.aspects.annotations.Timed)")    // TODO what will happen with this? how many calls?
  @Around("targetClassMethods() && @annotation(com.krotz.aop.aspects.annotations.Timed)")
  public Object timing(ProceedingJoinPoint pjp) throws Throwable {
    long start = System.currentTimeMillis();
    try {
      return pjp.proceed();
    } finally {
      long elapsedTime = System.currentTimeMillis() - start;
      System.out.println(elapsedTime + " milliseconds - " + pjp.getSignature());
    }
  }
}
