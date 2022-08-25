package com.loader.ninja.exception;

public class LoaderNinjaException extends RuntimeException {

    public LoaderNinjaException() {
    }

    public LoaderNinjaException(String message) {
        super(message);
    }

    public LoaderNinjaException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoaderNinjaException(Throwable cause) {
        super(cause);
    }

    public LoaderNinjaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
