package com.krotz.aop;

public class Main {
  public static void main(String[] args) throws InterruptedException {
    classic();
    inheritance();
    decorator();
    aop();
  }

  private static void classic() throws InterruptedException {
    System.out.println("----- START NO AOP -----");
    final A_SampleClassic noAop = new A_SampleClassic();

    System.out.println(noAop.heavyCall());
    System.out.println(noAop.heavyCall());

  }

  private static void inheritance() throws InterruptedException {
    System.out.println("----- START INHERITANCE -----");

    final Subject s = new SubjectWithLoggingAndCacheAndTiming();
    System.out.println(s.heavyCall());
    System.out.println(s.heavyCall());
  }

  private static void decorator() throws InterruptedException {
    System.out.println("----- START DECORATOR -----");

    Component concrete = new ConcreteComponent();
    Decorator timer = new TimerDecorator(concrete);
    Decorator logger = new LoggerDecorator(timer);

    logger.operation();

  }

  private static void aop() throws InterruptedException {
    System.out.println("----- START AOP -----");

    final D_SampleAOP aop = new D_SampleAOP();

    System.out.println(aop.heavyCall());
    System.out.println(aop.heavyCall());
  }
}
