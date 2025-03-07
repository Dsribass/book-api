package com.example.domain.usecase.book;

import com.example.domain.gateway.BookGateway;
import com.example.domain.usecase.UseCase;

public class DeleteBookUseCase extends UseCase<DeleteBookUseCase.Input, Void> {
    private final BookGateway bookGateway;

    public DeleteBookUseCase(BookGateway bookGateway) {
        this.bookGateway = bookGateway;
    }

    @Override
    protected Void run(DeleteBookUseCase.Input input) {
        bookGateway.deleteByIsbn(input.isbn);
        return null;
    }

    public record Input(String isbn) {
    }
}
