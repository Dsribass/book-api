package com.example.domain.usecase.book;

import com.example.domain.entity.Book;
import com.example.domain.gateway.BookGateway;
import com.example.domain.usecase.UseCase;

import java.util.List;

public class GetAllBooksUseCase extends UseCase<GetAllBooksUseCase.Input, List<Book>> {
    private final BookGateway bookGateway;

    public GetAllBooksUseCase(BookGateway bookGateway) {
        this.bookGateway = bookGateway;
    }

    @Override
    protected List<Book> run(GetAllBooksUseCase.Input input) {
        return bookGateway.findAll();
    }

    public record Input() {
    }
}
