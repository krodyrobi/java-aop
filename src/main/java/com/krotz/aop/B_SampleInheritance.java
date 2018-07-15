package com.krotz.aop;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

class Subject {
  int heavyCall() throws InterruptedException {
    Thread.sleep(3000);
    return new Random().nextInt(Integer.MAX_VALUE);
  }
}

class SubjectWithLogging extends Subject {
  String key = "com.krotz.aop.business.Subject.heavyCall()";

  int heavyCall() throws InterruptedException {
    System.out.println("Calling " + key);

    return super.heavyCall();
  }
}

class SubjectWithLoggingAndCache extends SubjectWithLogging {
  private final static Map<String, Object> cache = new ConcurrentHashMap<>();

  int heavyCall() throws InterruptedException {
    if (cache.containsKey(key)) {
      System.out.println("Fetching from cache");
      return (Integer) cache.get(key);
    }

    final Integer result = super.heavyCall();

    cache.put(key, result);
    System.out.println("Storing in cache");

    return result;
  }
}

class SubjectWithLoggingAndCacheAndTiming extends SubjectWithLoggingAndCache {
  int heavyCall() throws InterruptedException {
    long start = System.currentTimeMillis();
    try {
      return super.heavyCall();
    } finally {
      long elapsedTime = System.currentTimeMillis() - start;
      System.out.println(elapsedTime + " milliseconds - " + key);
    }
  }
}