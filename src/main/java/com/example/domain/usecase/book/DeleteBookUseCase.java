package com.example.domain.usecase.book;

import com.example.domain.gateway.BookGateway;
import com.example.domain.usecase.NoResult;
import com.example.domain.usecase.UseCase;

public class DeleteBookUseCase extends UseCase<DeleteBookUseCase.Input, NoResult> {
    private final BookGateway bookGateway;

    public DeleteBookUseCase(BookGateway bookGateway) {
        this.bookGateway = bookGateway;
    }

    @Override
    protected NoResult run(DeleteBookUseCase.Input input) {
        bookGateway.deleteByIsbn(input.isbn);
        return new NoResult();
    }

    public record Input(String isbn) {
    }
}
