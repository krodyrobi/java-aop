package com.krotz.aop;

import java.util.Random;


interface Component {
  int operation() throws InterruptedException;
}

abstract class Decorator implements Component {
  Component upstream;
}


class ConcreteComponent implements Component {
  public int operation() throws InterruptedException {
    Thread.sleep(3000);
    return new Random().nextInt(Integer.MAX_VALUE);
  }
}


class TimerDecorator extends Decorator {
  TimerDecorator(Component upstream) {
    this.upstream = upstream;
  }

  public int operation() throws InterruptedException {
    long start = System.currentTimeMillis();
    try {
      return upstream.operation();
    } finally {
      long elapsedTime = System.currentTimeMillis() - start;
      System.out.println(elapsedTime + " milliseconds");
    }
  }
}


class LoggerDecorator extends Decorator {
  private final Component upstream;

  LoggerDecorator(Component upstream) {
    this.upstream = upstream;
  }


  public int operation() throws InterruptedException {
    System.out.println("Calling function");
    return upstream.operation();
  }
}