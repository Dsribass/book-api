package com.example.domain.exception;

public class UnexpectedException extends DomainException {
    private final Exception originalException;

    public UnexpectedException(String message, Exception originalException) {
        super(message != null ? message : "Unexpected error occurred");
        this.originalException = originalException;
    }

    public UnexpectedException(Exception originalException) {
        this(null, originalException);
    }

    public Exception getOriginalException() {
        return originalException;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", getMessage(), originalException.toString());
    }
}
