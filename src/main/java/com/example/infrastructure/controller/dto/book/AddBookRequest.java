package com.example.infrastructure.controller.dto.book;

import com.example.domain.entity.Book;
import com.example.domain.value.ISBN;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Year;

public record AddBookRequest(
        @NotBlank(message = "ISBN is required")
        String isbn,
        @NotBlank(message = "Title is required")
        String title,
        @NotBlank(message = "Author is required")
        String author,
        @NotBlank(message = "Genre is required")
        String genre,
        @NotNull(message = "Published year is required")
        Integer publishedYear,
        @NotNull(message = "Total copies must not be null")
        @Min(value = 1, message = "Total copies must be at least 1")
        Integer totalCopies) {

    public Book toEntity() {
        return new Book(new ISBN(isbn), title, author, genre, Year.of(publishedYear), totalCopies);
    }

}
