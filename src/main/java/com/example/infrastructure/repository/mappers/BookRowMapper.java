package com.example.infrastructure.repository.mappers;

import com.example.domain.entity.Book;
import com.example.domain.value.ISBN;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;

public class BookRowMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Book(
                new ISBN(rs.getString("isbn")),
                rs.getString("title"),
                rs.getString("author"),
                rs.getString("genre"),
                Year.of(rs.getInt("published_year")),
                rs.getInt("total_copies"),
                rs.getInt("available_copies")
        );
    }
}
