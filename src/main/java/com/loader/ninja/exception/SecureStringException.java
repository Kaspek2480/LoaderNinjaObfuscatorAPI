package com.loader.ninja.exception;

public class SecureStringException extends LoaderNinjaException {

    public SecureStringException() {
    }

    public SecureStringException(String message) {
        super(message);
    }

    public SecureStringException(String message, Throwable cause) {
        super(message, cause);
    }

    public SecureStringException(Throwable cause) {
        super(cause);
    }

    public SecureStringException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
