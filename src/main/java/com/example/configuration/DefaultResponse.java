package com.example.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public record DefaultResponse(String message, Object data) {
    public DefaultResponse {
        if (message == null) {
            throw new IllegalArgumentException("Message cannot be null");
        }
    }

    public ResponseEntity<DefaultResponse> toResponseEntity(HttpStatus status) {
        return new ResponseEntity<>(this, status);
    }

    public ResponseEntity<DefaultResponse> toResponseEntity() {
        return toResponseEntity(HttpStatus.OK);
    }
}
