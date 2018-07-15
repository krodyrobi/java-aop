package com.krotz.aop;

public class Main {
  public static void main(String[] args) throws InterruptedException {
    noAop();
    inheritance();
    decorator();
    aop();
  }


  private static void noAop() throws InterruptedException {
    System.out.println("----- START NO AOP -----");
    final A_SampleNoAop noAop = new A_SampleNoAop();

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

    final D_SampleWithAop aop = new D_SampleWithAop();

    System.out.println(aop.heavyCall());
    System.out.println(aop.heavyCall());
  }
}
