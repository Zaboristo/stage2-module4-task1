package com.mjc.stage2;

public class ThreadSafeSingleton {
    private static volatile  ThreadSafeSingleton instance = new ThreadSafeSingleton();

    private ThreadSafeSingleton(){}

    public static synchronized ThreadSafeSingleton getInstance() {
        return instance;
    }
}
