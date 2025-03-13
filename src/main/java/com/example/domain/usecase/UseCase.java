package com.example.domain.usecase;

import com.example.domain.exception.DomainException;
import com.example.domain.exception.UnexpectedException;
import jakarta.annotation.Nonnull;

import java.util.Objects;

public abstract class UseCase<Input, Output> {
    abstract protected Output run(Input input);

    public Output execute(@Nonnull Input input) {
        Objects.requireNonNull(input);

        try {
            var result = run(input);
            Objects.requireNonNull(result);
            return result;
        } catch (Exception e) {
            if (e instanceof DomainException) {
                throw (DomainException) e;
            }

            throw new UnexpectedException(e.getMessage());
        }
    }
}
