package com.krotz.aop;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;


class A_SampleNoAop {
  private final static Map<String, Object> cache = new ConcurrentHashMap<>();

  int heavyCall() throws InterruptedException {
    String key = "com.krotz.aop.business.A_SampleNoAop.heavyCall()";
    System.out.println("Calling " + key);

    if (cache.containsKey(key)) {
      System.out.println("Fetching from cache");
      return (Integer) cache.get(key);
    }

    long start = System.currentTimeMillis();
    try {
      Thread.sleep(3000);
      Integer result = new Random().nextInt(Integer.MAX_VALUE);

      cache.put(key, result);
      System.out.println("Storing in cache");

      return result;
    } finally {
      long elapsedTime = System.currentTimeMillis() - start;
      System.out.println(elapsedTime + " milliseconds - " + key);
    }
  }
}
