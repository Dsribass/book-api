package com.example.infrastructure.repository;

import com.example.domain.entity.Book;
import com.example.domain.exception.ItemNotExistsException;
import com.example.domain.gateway.BookGateway;
import com.example.infrastructure.repository.mappers.BookRowMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class JdbcBookRepository implements BookGateway {
    private final JdbcTemplate jdbcTemplate;

    public JdbcBookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        var sql = """
                SELECT b.isbn, b.title, b.author, g.name AS genre, b.published_year, b.total_copies, b.available_copies
                FROM books AS b
                JOIN genres AS g ON b.genre_id = g.id
                WHERE b.isbn=?
                """;

        return jdbcTemplate.query(sql, new BookRowMapper(), isbn)
                .stream()
                .findFirst();
    }

    @Override
    public void save(Book book) {
        var sql = """
                INSERT INTO books (isbn, title, author, genre_id, published_year, total_copies, available_copies)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        var genreId = getGenreId(book.getGenre());

        jdbcTemplate.update(sql,
                book.getIsbn().value(),
                book.getTitle(),
                book.getAuthor(),
                genreId,
                book.getPublishedYear().getValue(),
                book.getTotalCopies(),
                book.getAvailableCopies()
        );
    }

    private Integer getGenreId(String genre) {
        var sql = """
                SELECT id
                FROM genres
                WHERE name=?
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getInt("id"), genre)
                .stream()
                .findFirst()
                .orElseThrow(
                        () -> new ItemNotExistsException("Genre not created: " + genre)
                );
    }

    @Override
    public void deleteByIsbn(String isbn) {
        var sql = """
                DELETE FROM books
                WHERE isbn=?
                """;

        jdbcTemplate.update(sql, isbn);
    }

    @Override
    public void update(Book book) {
        var sql = """
                UPDATE books
                SET title=?, author=?, genre_id=?, published_year=?, total_copies=?, available_copies=?
                WHERE isbn=?
                """;

        var genreId = getGenreId(book.getGenre());

        jdbcTemplate.update(sql,
                book.getTitle(),
                book.getAuthor(),
                genreId,
                book.getPublishedYear().getValue(),
                book.getTotalCopies(),
                book.getAvailableCopies(),
                book.getIsbn().value()
        );
    }

    @Override
    public List<Book> findAll() {
        var sql = """
                SELECT b.isbn, b.title, b.author, g.name AS genre, b.published_year, b.total_copies, b.available_copies
                FROM books AS b
                JOIN genres AS g ON b.genre_id = g.id
                """;

        return jdbcTemplate.query(sql, new BookRowMapper());
    }

    @Override
    public void saveGenre(String genre) {
        var sql = """
                INSERT INTO genres (name)
                VALUES (?)
                """;

        jdbcTemplate.update(sql, genre);
    }

    @Override
    public List<String> findAllGenres() {
        var sql = """
                SELECT name
                FROM genres
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("name"));
    }

    @Override
    public void deleteGenreByName(String genre) {
        var sql =  """ 
                DELETE FROM genres
                WHERE name=?
                """;

        jdbcTemplate.update(sql, genre);
    }

    @Override
    public Optional<String> findGenreByName(String genre) {
        var sql = """
                SELECT name
                FROM genres
                WHERE name=?
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("name"), genre)
                .stream()
                .findFirst();
    }
}
