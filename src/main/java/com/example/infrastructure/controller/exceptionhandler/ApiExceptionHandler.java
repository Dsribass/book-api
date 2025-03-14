package com.example.infrastructure.controller.exceptionhandler;

import com.example.domain.exception.ItemAlreadyExists;
import com.example.domain.exception.ItemNotExistsException;
import com.example.infrastructure.controller.response.ErrorResponse;
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
        final var message = e.toString();
        final var errorResponse = new ErrorResponse(
                message,
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
}
