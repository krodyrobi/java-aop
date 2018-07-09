package com.krotz.aop.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
public class B_CachingAspect {
  private final static Map<String, Object> cache = new ConcurrentHashMap<>();

  // TODO note the .. = 0 or more levels and (..) 0 or more arguments
  @Around("execution(* *..C_SampleWithAop.heavy*(..))")
  public Object cache(ProceedingJoinPoint pjp) throws Throwable {
    final String targetKey = pjp.getSignature().toString();

    if (cache.containsKey(targetKey)) {
      System.out.println("Fetching from cache");
      return cache.get(targetKey);
    }

    Object result = pjp.proceed();
    cache.put(targetKey, result);
    System.out.println("Storing in cache");
    return result;
  }
}
