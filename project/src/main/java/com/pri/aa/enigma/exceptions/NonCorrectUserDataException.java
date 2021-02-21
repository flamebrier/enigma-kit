package com.pri.aa.enigma.exceptions;

public class NonCorrectUserDataException extends RuntimeException {

    public NonCorrectUserDataException(String message) {
        super(message);
    }

    public NonCorrectUserDataException(String message, Throwable throwable) {
        super(message, throwable);
    }
}