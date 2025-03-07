package com.example.domain.usecase.book;

import com.example.domain.entity.Book;
import com.example.domain.exception.ItemAlreadyExists;
import com.example.domain.gateway.BookGateway;
import com.example.domain.usecase.UseCase;

public class AddBookUseCase extends UseCase<AddBookUseCase.Input, Void> {
    private final BookGateway bookGateway;

    public AddBookUseCase(BookGateway bookGateway) {
        this.bookGateway = bookGateway;
    }

    @Override
    protected Void run(AddBookUseCase.Input input) {
        if (bookGateway.findByIsbn(input.book.getIsbn()).isPresent()) {
            throw new ItemAlreadyExists("Book already exists");
        }

        bookGateway.save(input.book);
        return null;
    }

    public record Input(Book book) {
    }
}
