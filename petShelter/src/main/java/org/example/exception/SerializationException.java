package org.example.exception;

public class SerializationException extends Exception {
    public SerializationException(String message, Throwable cause) {
        super(message, cause);
    }
}