package com.example.domain.gateway;

import com.example.domain.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookGateway {
    Optional<Book> findByIsbn(String isbn);

    void save(Book book);

    void deleteByIsbn(String isbn);

    void update(Book book);

    List<Book> findAll();

    void saveGenre(String genre);

    List<String> findAllGenres();

    Optional<String> findGenreByName(String genre);

    void deleteGenreByName(String genre);
}
