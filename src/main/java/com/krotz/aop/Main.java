package com.krotz.aop;

import com.krotz.aop.business.Account;
import com.krotz.aop.business.Session;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final int krotzId = 1;
        final int otherId = 2;

        final Account joshua = new Account(krotzId, 10);
        final Account henry = new Account(otherId, 1000);

        final Session session = Session.getInstance();
        session.setActiveUserId(otherId);

//        joshua.getBalance();

        System.out.println(joshua.heavyRandom());
        System.out.println(joshua.heavyRandom());

        try {
            joshua.failingOperation();
        } catch(Exception e) {
            System.out.println("Caught " + e.getClass() + " message " + e.getMessage());
        }
    }

    // loadtime compile post-compile
    // providers, your stuff, if you have already compiled stuff and choose to merge them
}
