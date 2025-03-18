package com.example.domain.utils;

import com.example.domain.exception.DomainException;
import com.example.domain.exception.UnexpectedException;

import java.util.Objects;

public abstract class UseCase<Input, Output> {
    private final Logger logger;

    protected UseCase(Logger logger) {
        this.logger = logger;
    }

    abstract protected Output run(Input input);

    public Output execute(Input input) {
        Objects.requireNonNull(input, "Use Case: Input must not be null");

        try {
            var result = run(input);
            Objects.requireNonNull(result, "Use Case: Output must not be null");
            return result;
        } catch (Exception e) {
            logger.log(e.toString(), e);
            if (e instanceof DomainException) {
                throw (DomainException) e;
            }

            throw new UnexpectedException(e);
        }
    }
}
