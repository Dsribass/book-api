package com.example.domain.exception;

public class ItemAlreadyExists extends DomainException {
    public ItemAlreadyExists(String message) {
        super(message);
    }
}
