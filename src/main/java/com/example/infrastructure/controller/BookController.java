package com.example.infrastructure.controller;

import com.example.domain.usecase.book.*;
import com.example.domain.usecase.book.genre.AddGenreUseCase;
import com.example.domain.usecase.book.genre.DeleteGenreUseCase;
import com.example.domain.usecase.book.genre.GetAllGenresUseCase;
import com.example.domain.value.ISBN;
import com.example.infrastructure.controller.dto.book.AddBookRequest;
import com.example.infrastructure.controller.dto.book.AddGenreRequest;
import com.example.infrastructure.controller.dto.book.BookResponse;
import com.example.infrastructure.controller.dto.book.UpdateBookRequest;
import com.example.infrastructure.controller.utils.DefaultResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/books")
public class BookController {
    private final AddBookUseCase addBookUseCase;
    private final GetBookByIsbnUseCase getBookByIsbnUseCase;
    private final GetAllBooksUseCase getAllBooksUseCase;
    private final UpdateBookUseCase updateBookUseCase;
    private final DeleteBookUseCase deleteBookUseCase;
    private final AddGenreUseCase addGenreUseCase;
    private final GetAllGenresUseCase getAllGenresUseCase;
    private final DeleteGenreUseCase deleteGenreUseCase;

    public BookController(AddBookUseCase addBookUseCase,
                          GetBookByIsbnUseCase getBookByIsbnUseCase,
                          GetAllBooksUseCase getAllBooksUseCase,
                          UpdateBookUseCase updateBookUseCase,
                          DeleteBookUseCase deleteBookUseCase,
                          AddGenreUseCase addGenreUseCase,
                          GetAllGenresUseCase getAllGenresUseCase,
                          DeleteGenreUseCase deleteGenreUseCase

    ) {
        this.addBookUseCase = addBookUseCase;
        this.getBookByIsbnUseCase = getBookByIsbnUseCase;
        this.getAllBooksUseCase = getAllBooksUseCase;
        this.updateBookUseCase = updateBookUseCase;
        this.deleteBookUseCase = deleteBookUseCase;
        this.addGenreUseCase = addGenreUseCase;
        this.getAllGenresUseCase = getAllGenresUseCase;
        this.deleteGenreUseCase = deleteGenreUseCase;
    }

    @PostMapping
    public ResponseEntity<DefaultResponse> add(@RequestBody @Valid AddBookRequest request) {
        addBookUseCase.execute(new AddBookUseCase.Input(request.toEntity()));

        return new DefaultResponse("Book added successfully", null)
                .toResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<DefaultResponse> update(
            @PathVariable String isbn,
            @RequestBody @Valid UpdateBookRequest request
    ) {
        updateBookUseCase.execute(new UpdateBookUseCase.Input(
                request.toEntity(new ISBN(isbn)
                )));
        return new DefaultResponse("Book updated successfully", null)
                .toResponseEntity();
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<DefaultResponse> delete(@PathVariable String isbn) {
        deleteBookUseCase.execute(new DeleteBookUseCase.Input(new ISBN(isbn)));
        return new DefaultResponse(
                "Book deleted successfully",
                Map.of("deletedIsbn", isbn)
        ).toResponseEntity();
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<DefaultResponse> get(@PathVariable String isbn) {
        var book = getBookByIsbnUseCase.execute(new GetBookByIsbnUseCase.Input(new ISBN(isbn)));

        return new DefaultResponse(
                "Book found",
                BookResponse.fromEntity(book)
        ).toResponseEntity();
    }

    @GetMapping
    public ResponseEntity<DefaultResponse> getAll() {
        var books = getAllBooksUseCase.execute(new GetAllBooksUseCase.Input());
        return new DefaultResponse(
                "List of books",
                books.stream()
                        .map(BookResponse::fromEntity)
                        .toList()
        ).toResponseEntity();
    }

    @GetMapping("genres/")
    public ResponseEntity<DefaultResponse> getAllGenres() {
        var genres = getAllGenresUseCase.execute(new GetAllGenresUseCase.Input());
        return new DefaultResponse("List of genres", genres).toResponseEntity();
    }

    @PostMapping("/genres")
    public ResponseEntity<DefaultResponse> addGenre(@RequestBody @Valid AddGenreRequest request) {
        addGenreUseCase.execute(new AddGenreUseCase.Input(request.name()));

        return new DefaultResponse("Genre added successfully", null)
                .toResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/genres/{name}")
    public ResponseEntity<DefaultResponse> deleteGenre(@PathVariable String name) {
        deleteGenreUseCase.execute(new DeleteGenreUseCase.Input(name));
        return new DefaultResponse(
                "Genre deleted successfully",
                Map.of("deletedGenre", name)
        ).toResponseEntity();
    }
}
