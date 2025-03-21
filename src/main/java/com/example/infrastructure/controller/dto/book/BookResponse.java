package com.example.infrastructure.controller.dto.book;

import com.example.domain.entity.Book;

public record BookResponse(
        String isbn,
        String title,
        String author,
        String genre,
        Integer publishedYear,
        Integer totalCopies,
        Integer availableCopies
) {
    public static BookResponse fromEntity(Book book) {
        return new BookResponse(
                book.getIsbn().value(),
                book.getTitle(),
                book.getAuthor(),
                book.getGenre(),
                book.getPublishedYear().getValue(),
                book.getTotalCopies(),
                book.getAvailableCopies()
        );
    }
}
