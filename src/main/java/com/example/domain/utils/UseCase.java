package com.example.domain.utils;

import com.example.domain.exception.DomainException;
import com.example.domain.exception.UnexpectedException;
import jakarta.annotation.Nonnull;

import java.util.Objects;

public abstract class UseCase<Input, Output> {
    private final Logger logger;

    protected UseCase(@Nonnull Logger logger) {
        this.logger = logger;
    }

    abstract protected Output run(Input input);

    public Output execute(@Nonnull Input input) {
        Objects.requireNonNull(input);

        try {
            var result = run(input);
            Objects.requireNonNull(result);
            return result;
        } catch (Exception e) {
            logger.log(e.toString());
            if (e instanceof DomainException) {
                throw (DomainException) e;
            }

            throw new UnexpectedException(e);
        }
    }
}
