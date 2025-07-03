package com.swissre.app.exceptions;

public class EmptyCsvFileException extends RuntimeException {
    public EmptyCsvFileException(String message) {
        super(message);
    }

    public EmptyCsvFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
