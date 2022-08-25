package com.loader.ninja.spy.impl;

import com.loader.ninja.spy.ICallMethodSpy;

public class NewCallMethodSpy implements ICallMethodSpy {

    @Override
    public String getCallerMethod(int position) {
        StackTraceElement[] context = Thread.currentThread().getStackTrace(); //getStackTrace();
        if (position >= context.length || position < 0)
            throw new ArrayIndexOutOfBoundsException();

        return context[position].getClassName();
    }

    @Override
    public String fetchCallerMethod(int depth) {
        StackTraceElement[] context = Thread.currentThread().getStackTrace(); //getStackTrace();
        depth = Math.min(context.length, depth);
        for (int i = 1; i < depth; i++) {
            String caller = context[i].getClassName();
            if (!caller.equals("com.loader.ninja.spy.impl.NewCallMethodSpy") && !caller.equals("com.loader.ninja.spy.ICallMethodSpy"))
                return caller;
        }

        return null;
    }

    private StackTraceElement[] getStackTrace() {
        return new Throwable().getStackTrace();
    }
}
