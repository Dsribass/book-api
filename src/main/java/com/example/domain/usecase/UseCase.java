package com.example.domain.usecase;

import com.example.domain.exception.DomainException;
import com.example.domain.exception.UnexpectedException;

import java.util.Objects;

public abstract class UseCase<Input, Output> {
    abstract protected Output run(Input input);

    public Output execute(Input input) {
        Objects.requireNonNull(input);

        try {
            return run(input);
        } catch (Exception e) {
            if (e instanceof DomainException) {
                throw (DomainException) e;
            }

            throw new UnexpectedException(e.getMessage());
        }
    }
}
