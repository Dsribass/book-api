package com.example.domain.usecase.book;

import com.example.domain.entity.Book;
import com.example.domain.exception.ItemNotExistsException;
import com.example.domain.gateway.BookGateway;
import com.example.domain.utils.Logger;
import com.example.domain.utils.UseCase;
import com.example.domain.value.ISBN;

public class GetBookByIsbnUseCase extends UseCase<GetBookByIsbnUseCase.Input, Book> {
    private final BookGateway bookGateway;

    public GetBookByIsbnUseCase(BookGateway bookGateway, Logger logger) {
        super(logger);
        logger.setClazz(GetBookByIsbnUseCase.class);
        this.bookGateway = bookGateway;
    }

    @Override
    protected Book run(GetBookByIsbnUseCase.Input input) {
        return bookGateway.findByIsbn(input.isbn.value())
                .orElseThrow(() -> new ItemNotExistsException("Book not found"));
    }

    public record Input(ISBN isbn) {
    }
}
