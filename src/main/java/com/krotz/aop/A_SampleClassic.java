package com.krotz.aop;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;


class A_SampleClassic {
  private final static Map<String, Object> cache = new ConcurrentHashMap<>();

  // TODO can you guess what it does in 5 seconds or less?

  int heavyCall() throws InterruptedException {
    String key = "com.krotz.aop.business.A_SampleClassic.heavyCall()";
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

  // TODO now imagine these lines duplicated everywhere -> Crosscutting concerns

  /*
    What are crosscutting concerns
    Why split them from business logic
  */
}
