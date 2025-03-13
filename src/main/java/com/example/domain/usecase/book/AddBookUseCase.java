package com.example.domain.usecase.book;

import com.example.domain.entity.Book;
import com.example.domain.exception.ItemAlreadyExists;
import com.example.domain.gateway.BookGateway;
import com.example.domain.utils.Logger;
import com.example.domain.utils.NoResult;
import com.example.domain.utils.UseCase;

public class AddBookUseCase extends UseCase<AddBookUseCase.Input, NoResult> {
    private final BookGateway bookGateway;

    public AddBookUseCase(BookGateway bookGateway, Logger logger) {
        super(logger);
        logger.setClazz(AddBookUseCase.class);
        this.bookGateway = bookGateway;
    }

    @Override
    protected NoResult run(AddBookUseCase.Input input) {
        if (bookGateway.findByIsbn(input.book.getIsbn()).isPresent()) {
            throw new ItemAlreadyExists("Book already exists");
        }

        bookGateway.save(input.book);
        return new NoResult();
    }

    public record Input(Book book) {
    }
}
