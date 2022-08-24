package com.loader.ninja.security;

public class Ticker {
    protected static native void tick();

    protected static void start() {
        new Thread(() -> {
            while (true) {
                try {
                    tick();
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
