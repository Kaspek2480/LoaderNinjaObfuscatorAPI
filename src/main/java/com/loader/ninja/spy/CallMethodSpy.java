package com.loader.ninja.spy;

import com.loader.ninja.spy.impl.NewCallMethodSpy;
import com.loader.ninja.spy.impl.OldCallMethodSpy;

public enum CallMethodSpy {

    INSTANCE;

    private final ICallMethodSpy spy;

    CallMethodSpy() {
        spy = Float.parseFloat(System.getProperty("java.class.version")) >= 60 ? new NewCallMethodSpy() : new OldCallMethodSpy();
    }

    public ICallMethodSpy getSpy() {
        return spy;
    }
}
