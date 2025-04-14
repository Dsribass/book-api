package com.example.domain.usecase.book.genre;

import com.example.domain.exception.ItemAlreadyExists;
import com.example.domain.gateway.BookGateway;
import com.example.domain.utils.Logger;
import com.example.domain.utils.NoResult;
import com.example.domain.utils.UseCase;

public class AddGenreUseCase extends UseCase<AddGenreUseCase.Input, NoResult> {
    private final BookGateway bookGateway;

    public AddGenreUseCase(BookGateway bookGateway, Logger logger) {
        super(logger);
        logger.setClazz(AddGenreUseCase.class);
        this.bookGateway = bookGateway;
    }

    @Override
    protected NoResult run(AddGenreUseCase.Input input) {
        if (bookGateway.findGenreByName(input.genre).isPresent()) {
            throw new ItemAlreadyExists("Genre already exists");
        }

        bookGateway.saveGenre(input.genre);
        return new NoResult();
    }

    public record Input(String genre) {
    }
}
