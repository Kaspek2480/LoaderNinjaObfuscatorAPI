package com.loader.ninja.exception;

public class SecretNotFoundException extends LoaderNinjaException {

    public SecretNotFoundException() {
    }

    public SecretNotFoundException(String message) {
        super(message);
    }

    public SecretNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SecretNotFoundException(Throwable cause) {
        super(cause);
    }

    public SecretNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
