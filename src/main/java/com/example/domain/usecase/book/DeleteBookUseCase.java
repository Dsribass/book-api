package com.example.domain.usecase.book;

import com.example.domain.gateway.BookGateway;
import com.example.domain.utils.Logger;
import com.example.domain.utils.NoResult;
import com.example.domain.utils.UseCase;
import com.example.domain.value.ISBN;

public class DeleteBookUseCase extends UseCase<DeleteBookUseCase.Input, NoResult> {
    private final BookGateway bookGateway;

    public DeleteBookUseCase(BookGateway bookGateway, Logger logger) {
        super(logger);
        logger.setClazz(DeleteBookUseCase.class);
        this.bookGateway = bookGateway;
    }

    @Override
    protected NoResult run(DeleteBookUseCase.Input input) {
        bookGateway.deleteByIsbn(input.isbn.value());
        return new NoResult();
    }

    public record Input(ISBN isbn) {
    }
}
