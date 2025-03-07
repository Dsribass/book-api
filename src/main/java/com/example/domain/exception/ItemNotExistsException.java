package com.example.domain.exception;

public class ItemNotExistsException extends DomainException {
    public ItemNotExistsException(String message) {
        super(message);
    }
}
