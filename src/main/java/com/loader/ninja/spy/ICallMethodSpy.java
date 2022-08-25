package com.loader.ninja.spy;

public interface ICallMethodSpy {

    String getCallerMethod(int position);

    String fetchCallerMethod(int depth);

    default String fetchCallerMethod() {
        return fetchCallerMethod(5);
    }
}
