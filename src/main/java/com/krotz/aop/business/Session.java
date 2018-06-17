package com.krotz.aop.business;

public class Session {
    private static Session ourInstance = new Session();

    private int activeUserId;

    public static Session getInstance() {
        return ourInstance;
    }

    private Session() {}

    public int getActiveUserId() {
        return activeUserId;
    }

    public void setActiveUserId(int activeUserId) {
        this.activeUserId = activeUserId;
    }
}
