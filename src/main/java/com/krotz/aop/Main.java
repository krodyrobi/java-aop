package com.krotz.aop;

public class Main {
  public static void main(String[] args) throws InterruptedException {
    noAop();
    decorator();
    aop();
  }

  private static void noAop() throws InterruptedException {
    System.out.println("----- START NO AOP -----");
    final A_SampleNoAop noAop = new A_SampleNoAop();

    System.out.println(noAop.heavyCall());
    System.out.println(noAop.heavyCall());

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

    final C_SampleWithAop aop = new C_SampleWithAop();

    System.out.println(aop.heavyCall());
    System.out.println(aop.heavyCall());
  }


  // TODO check this
  //    Autorization
  //  Error translation
  //    Transaction
  // loadtime compile post-compile
  // providers, your stuff, if you have already compiled stuff and choose to merge them
}
