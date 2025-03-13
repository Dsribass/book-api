package com.example.domain.usecase.book;

import com.example.domain.entity.Book;
import com.example.domain.exception.ItemNotExistsException;
import com.example.domain.gateway.BookGateway;
import com.example.domain.usecase.UseCase;

public class GetBookByIsbnUseCase extends UseCase<GetBookByIsbnUseCase.Input, Book> {
    private final BookGateway bookGateway;

    public GetBookByIsbnUseCase(BookGateway bookGateway) {
        this.bookGateway = bookGateway;
    }

    @Override
    protected Book run(GetBookByIsbnUseCase.Input input) {
        return bookGateway.findByIsbn(input.isbn)
                .orElseThrow(() -> new ItemNotExistsException("Book not found"));
    }

    public record Input(String isbn) {
    }
}
