package com.loader.ninja.spy.impl;

import com.loader.ninja.spy.ICallMethodSpy;

@Deprecated
public class OldCallMethodSpy extends SecurityManager implements ICallMethodSpy {

    @Override
    public String getCallerMethod(int position) {
        Class<?>[] context = getClassContext();
        if (position >= context.length || position < 0)
            throw new ArrayIndexOutOfBoundsException();

        return context[position].getName();
    }

    @Override
    public String fetchCallerMethod(int depth) {
        Class<?>[] context = getClassContext();
        depth = Math.min(context.length, depth);
        for (int i = 1; i < depth; i++) {
            String caller = context[i].getName();
            if (!caller.equals("com.loader.ninja.spy.impl.OldCallMethodSpy") && !caller.equals("com.loader.ninja.spy.ICallMethodSpy"))
                return caller;
        }

        return null;
    }
}
