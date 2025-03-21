package com.example.configuration;

import com.example.domain.gateway.BookGateway;
import com.example.domain.usecase.book.*;
import com.example.domain.utils.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Injector {
    public Logger getLogger() {
        return new Logger() {
            private org.slf4j.Logger logger;

            @Override
            public void log(String message, Throwable throwable) {
                if (logger == null) {
                    logger = LoggerFactory.getLogger(getClazz());
                }
                logger.error(message, throwable);
            }
        };
    }

    @Bean
    public AddBookUseCase addBookUseCase(BookGateway bookGateway) {
        return new AddBookUseCase(bookGateway, getLogger());
    }

    @Bean
    public GetBookByIsbnUseCase getBookByIsbnUseCase(BookGateway bookGateway) {
        return new GetBookByIsbnUseCase(bookGateway, getLogger());
    }

    @Bean
    public GetAllBooksUseCase getAllBooksUseCase(BookGateway bookGateway) {
        return new GetAllBooksUseCase(bookGateway, getLogger());
    }

    @Bean
    public DeleteBookUseCase deleteBookUseCase(BookGateway bookGateway) {
        return new DeleteBookUseCase(bookGateway, getLogger());
    }

    @Bean
    public UpdateBookUseCase updateBookUseCase(BookGateway bookGateway) {
        return new UpdateBookUseCase(bookGateway, getLogger());
    }
}
