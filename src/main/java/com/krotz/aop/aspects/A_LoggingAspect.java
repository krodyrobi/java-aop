package com.krotz.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;
import java.util.stream.Collectors;


@Aspect
public class A_LoggingAspect {
  @Pointcut("execution(* com.krotz.aop.D_SampleWithAop.*(..))")
  public void targetClassMethods() {
  }

  @Before("targetClassMethods()")
  public void log(JoinPoint joinPoint) {
    final String args = Arrays.stream(joinPoint.getArgs()).map(Object::toString).collect(Collectors.joining(","));
    System.out.println("Calling " + joinPoint.getSignature() + " " + args);
  }
}
