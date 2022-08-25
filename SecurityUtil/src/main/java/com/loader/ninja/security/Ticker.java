package com.loader.ninja.security;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Ticker {

    protected static native void tick();

    protected static void start() {
        Executors.newSingleThreadScheduledExecutor()
                .scheduleWithFixedDelay(Ticker::tick, 0, 250, TimeUnit.MILLISECONDS);
    }
}
