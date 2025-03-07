package com.example.domain.usecase.book;

import com.example.domain.entity.Book;
import com.example.domain.exception.ItemNotExistsException;
import com.example.domain.gateway.BookGateway;
import com.example.domain.usecase.UseCase;

public class UpdateBookUseCase extends UseCase<UpdateBookUseCase.Input, Void> {
    private final BookGateway bookGateway;

    public UpdateBookUseCase(BookGateway bookGateway) {
        this.bookGateway = bookGateway;
    }

    @Override
    protected Void run(UpdateBookUseCase.Input input) {
        if (bookGateway.findByIsbn(input.book.getIsbn()).isEmpty()) {
            throw new ItemNotExistsException("Book not exists");
        }

        bookGateway.update(input.book);
        return null;
    }

    public record Input(Book book) {
    }
}
