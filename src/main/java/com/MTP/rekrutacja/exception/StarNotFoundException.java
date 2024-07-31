package com.MTP.rekrutacja.exception;

/**
 * Custom exception for when a star is not found.
 */
public class StarNotFoundException extends ApplicationException {

    /**
     * Constructs a new StarNotFoundException with the specified detail message.
     *
     * @param message the detail message
     */
    public StarNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new StarNotFoundException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause   the cause of the exception
     */
    public StarNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}