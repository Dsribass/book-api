package com.example.domain.usecase.book;

import com.example.domain.entity.Book;
import com.example.domain.exception.ItemNotExistsException;
import com.example.domain.gateway.BookGateway;
import com.example.domain.utils.Logger;
import com.example.domain.utils.NoResult;
import com.example.domain.utils.UseCase;

public class UpdateBookUseCase extends UseCase<UpdateBookUseCase.Input, NoResult> {
    private final BookGateway bookGateway;

    public UpdateBookUseCase(BookGateway bookGateway, Logger logger) {
        super(logger);
        logger.setClazz(UpdateBookUseCase.class);
        this.bookGateway = bookGateway;
    }

    @Override
    protected NoResult run(UpdateBookUseCase.Input input) {
        if (bookGateway.findByIsbn(input.book.getIsbn()).isEmpty()) {
            throw new ItemNotExistsException("Book with ISBN " + input.book.getIsbn() + " does not exist");
        }

        bookGateway.update(input.book);
        return new NoResult();
    }

    public record Input(Book book) {
    }
}
