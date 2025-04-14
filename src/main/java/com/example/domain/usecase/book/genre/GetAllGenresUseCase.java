package com.example.domain.usecase.book.genre;

import com.example.domain.gateway.BookGateway;
import com.example.domain.utils.Logger;
import com.example.domain.utils.UseCase;

import java.util.List;

public class GetAllGenresUseCase extends UseCase<GetAllGenresUseCase.Input, List<String>> {
    private final BookGateway bookGateway;

    public GetAllGenresUseCase(BookGateway bookGateway, Logger logger) {
        super(logger);
        logger.setClazz(GetAllGenresUseCase.class);
        this.bookGateway = bookGateway;
    }

    @Override
    protected List<String> run(GetAllGenresUseCase.Input input) {
        return bookGateway.findAllGenres();
    }

    public record Input() {
    }
}
