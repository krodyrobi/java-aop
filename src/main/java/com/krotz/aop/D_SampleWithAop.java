package com.krotz.aop;

import com.krotz.aop.aspects.annotations.Timed;

import java.util.Random;

class D_SampleWithAop {
  @Timed
  int heavyCall() throws InterruptedException {
    Thread.sleep(3000);
    return new Random().nextInt(Integer.MAX_VALUE);
  }
}
