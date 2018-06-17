package com.krotz.aop.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
public class B_CachingAspect {
    private final static Map<String, Object> cache = new ConcurrentHashMap<>();

    // TODO key computation can take into account method args hashes and stuff
    @Around("execution(* *.heavy*())")
    public Object cache(ProceedingJoinPoint pjp) throws Throwable {
        final String targetKey = pjp.getSignature().toString();

        if ( cache.containsKey(targetKey) ) {
            System.out.println("Fetching from cache");
            return cache.get(targetKey);
        }

        Object result = pjp.proceed();
        cache.put(targetKey, result);
        System.out.println("Storing in cache");
        return result;
    }
}
