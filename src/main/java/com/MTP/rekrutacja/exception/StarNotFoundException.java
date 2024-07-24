package com.MTP.rekrutacja.exception;

public class StarNotFoundException extends ApplicationException {

    public StarNotFoundException(String message) {
        super(message);
    }

    public StarNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}