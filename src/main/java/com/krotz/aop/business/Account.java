package com.krotz.aop.business;

import com.krotz.aop.aspects.annotations.Timed;

import java.util.Random;

public class Account {
    private int balance;
    private final int accountId;

    public Account(int accountId, int balance) {
        this.balance = balance;
        this.accountId = accountId;
    }

    public boolean withdraw(int amount) {
        if(balance < amount) {
            return false;
        }

        balance -= amount;
        return true;
    }

    @Timed
    public int heavyRandom() throws InterruptedException {
        Thread.sleep(3000);
        return new Random().nextInt(Integer.MAX_VALUE);
    }

    public int failingOperation() {
        throw new RuntimeException("Wut");
    }

    public int getAccountId() {
        return accountId;
    }

    public int getBalance() {
        return balance;
    }
}
