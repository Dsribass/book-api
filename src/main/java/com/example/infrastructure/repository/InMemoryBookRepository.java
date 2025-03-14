package com.example.infrastructure.repository;

import com.example.domain.entity.Book;
import com.example.domain.gateway.BookGateway;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public final class InMemoryBookRepository implements BookGateway {
    private final List<Book> books = new ArrayList<>(
            List.of(
                    new Book("978-0-306-40615-7", "Clean Code", "Robert C. Martin", "Software", 2008, 10, 10),
                    new Book("978-0-201-61622-4", "Design Patterns", "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides", "Software", 1994, 5, 5),
                    new Book("978-0-13-235088-4", "Refactoring", "Martin Fowler", "Software", 1999, 7, 7)
            )
    );

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return books.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst();
    }

    @Override
    public void save(Book book) {
        books.add(book);
    }

    @Override
    public void deleteByIsbn(String isbn) {
        books.removeIf(book -> book.getIsbn().equals(isbn));
    }

    @Override
    public void update(Book book) {
        books.removeIf(b -> b.getIsbn().equals(book.getIsbn()));
        books.add(book);
    }

    @Override
    public List<Book> findAll() {
        return books;
    }
}
