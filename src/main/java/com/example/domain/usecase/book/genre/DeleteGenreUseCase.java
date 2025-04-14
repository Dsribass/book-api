package com.example.domain.usecase.book.genre;

import com.example.domain.exception.ItemNotExistsException;
import com.example.domain.gateway.BookGateway;
import com.example.domain.utils.Logger;
import com.example.domain.utils.NoResult;
import com.example.domain.utils.UseCase;

public class DeleteGenreUseCase extends UseCase<DeleteGenreUseCase.Input, NoResult> {
    private final BookGateway bookGateway;

    public DeleteGenreUseCase(BookGateway bookGateway, Logger logger) {
        super(logger);
        logger.setClazz(DeleteGenreUseCase.class);
        this.bookGateway = bookGateway;
    }

    @Override
    protected NoResult run(DeleteGenreUseCase.Input input) {
        if (bookGateway.findGenreByName(input.genre).isEmpty()) {
            throw new ItemNotExistsException("Genre does not exist");
        }

        bookGateway.deleteGenreByName(input.genre);
        return new NoResult();
    }

    public record Input(String genre) {
    }
}
