package com.example.infrastructure.controller.utils;

import com.example.domain.exception.DomainException;
import com.example.domain.exception.ItemAlreadyExists;
import com.example.domain.exception.ItemNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnexpectedException(Exception e) {
        final var errorResponse = new ErrorResponse(
                "Internal server error",
                HttpStatus.INTERNAL_SERVER_ERROR
        );
        return errorResponse.toResponseEntity();
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Object> handleDomainException(DomainException e) {
        final var errorResponse = new ErrorResponse(
                e.toString(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
        return errorResponse.toResponseEntity();
    }

    @ExceptionHandler(ItemAlreadyExists.class)
    public ResponseEntity<Object> handleItemAlreadyExists(ItemAlreadyExists e) {
        var errorResponse = new ErrorResponse(
                e.getMessage(),
                HttpStatus.CONFLICT
        );
        return errorResponse.toResponseEntity();
    }

    @ExceptionHandler(ItemNotExistsException.class)
    public ResponseEntity<Object> handleItemNotExistsException(ItemNotExistsException e) {
        var errorResponse = new ErrorResponse(
                e.getMessage(),
                HttpStatus.NOT_FOUND
        );
        return errorResponse.toResponseEntity();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleInvalidArgument(MethodArgumentNotValidException e) {
        var errorMap = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(
                        Collectors.toMap(
                                FieldError::getField,
                                fieldError -> fieldError.getDefaultMessage() != null ?
                                        fieldError.getDefaultMessage() :
                                        "Invalid value"
                        )
                );

        var errorResponse = new ErrorResponse(
                "Invalid request",
                errorMap,
                HttpStatus.BAD_REQUEST
        );
        return errorResponse.toResponseEntity();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException e) {
        var errorResponse = new ErrorResponse(
                e.getMessage(),
                HttpStatus.BAD_REQUEST
        );
        return errorResponse.toResponseEntity();
    }
}
