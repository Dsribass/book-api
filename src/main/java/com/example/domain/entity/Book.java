package com.example.domain.entity;

import java.util.Objects;

public final class Book {
    private final String isbn;
    private final String title;
    private final String author;
    private final String genre;
    private final Integer publishedYear;
    private Integer totalCopies;
    private Integer availableCopies;

    public Book(String isbn,
                String title,
                String author,
                String genre,
                Integer publishedYear,
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

    public void lend() {
        if (availableCopies > 0) {
            availableCopies--;
        }
    }

    public void returnBook() {
        availableCopies++;
    }

    public void addCopies(Integer copies) {
        totalCopies += copies;
        availableCopies += copies;
    }

    public void removeCopies(Integer copies) {
        totalCopies -= copies;
        availableCopies -= copies;
    }

    public String getIsbn() {
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

    public Integer getPublishedYear() {
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
