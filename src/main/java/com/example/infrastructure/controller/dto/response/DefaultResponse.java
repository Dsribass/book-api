package com.example.infrastructure.controller.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public record DefaultResponse(String message, Object data) {
    public DefaultResponse {
        assert message != null : "Message must not be null";
    }

    public ResponseEntity<DefaultResponse> toResponseEntity(HttpStatus status) {
        return new ResponseEntity<>(this, status);
    }

    public ResponseEntity<DefaultResponse> toResponseEntity() {
        return toResponseEntity(HttpStatus.OK);
    }
}
