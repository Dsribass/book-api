package com.example.domain.entity;

import com.example.domain.value.ISBN;

import java.time.Year;
import java.util.Objects;

public class Book {
    private final ISBN isbn;
    private final String title;
    private final String author;
    private final String genre;
    private final Year publishedYear;
    private final Integer totalCopies;
    private Integer availableCopies;

    public Book(ISBN isbn,
                String title,
                String author,
                String genre,
                Year publishedYear,
                Integer totalCopies,
                Integer availableCopies
    ) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publishedYear = publishedYear;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
    }

    public Book(ISBN isbn,
                String title,
                String author,
                String genre,
                Year publishedYear,
                Integer totalCopies
    ) {
        this(isbn, title, author, genre, publishedYear, totalCopies, totalCopies);
    }

    public void lendBook() {
        if (availableCopies <= 0) {
            throw new IllegalStateException("No copies available");
        }

        availableCopies--;
    }

    public void returnBook() {
        if (availableCopies >= totalCopies) {
            throw new IllegalStateException("All copies are already available");
        }

        availableCopies++;
    }

    public ISBN getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public Year getPublishedYear() {
        return publishedYear;
    }

    public Integer getTotalCopies() {
        return totalCopies;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn) &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                Objects.equals(genre, book.genre) &&
                Objects.equals(publishedYear, book.publishedYear) &&
                Objects.equals(totalCopies, book.totalCopies) &&
                Objects.equals(availableCopies, book.availableCopies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, title, author, genre, publishedYear, totalCopies, availableCopies);
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", publishedYear=" + publishedYear +
                ", totalCopies=" + totalCopies +
                ", availableCopies=" + availableCopies +
                '}';
    }
}
