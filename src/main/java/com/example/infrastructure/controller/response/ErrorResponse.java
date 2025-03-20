package com.example.infrastructure.controller.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.ZonedDateTime;
import java.util.Map;

public record ErrorResponse(
        String message,
        ZonedDateTime timestamp,
        Map<String, String> validationErrors,
        HttpStatus status
) {
    public ErrorResponse(
            String message,
            HttpStatus status
    ) {
        this(message, ZonedDateTime.now(), Map.of(), status);
    }

    public ErrorResponse(
            String message,
            Map<String, String> errors,
            HttpStatus status
    ) {
        this(message, ZonedDateTime.now(), errors, status);
    }

    public ResponseEntity<Object> toResponseEntity() {
        return ResponseEntity.status(status).body(this);
    }
}