package com.example.infrastructure.controller;

import com.example.domain.usecase.book.*;
import com.example.domain.value.ISBN;
import com.example.infrastructure.controller.dto.AddBookDTO;
import com.example.infrastructure.controller.dto.UpdateBookDTO;
import com.example.infrastructure.controller.response.DefaultResponse;
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

    public BookController(AddBookUseCase addBookUseCase,
                          GetBookByIsbnUseCase getBookByIsbnUseCase,
                          GetAllBooksUseCase getAllBooksUseCase,
                          UpdateBookUseCase updateBookUseCase,
                          DeleteBookUseCase deleteBookUseCase
    ) {
        this.addBookUseCase = addBookUseCase;
        this.getBookByIsbnUseCase = getBookByIsbnUseCase;
        this.getAllBooksUseCase = getAllBooksUseCase;
        this.updateBookUseCase = updateBookUseCase;
        this.deleteBookUseCase = deleteBookUseCase;
    }

    @PostMapping
    public ResponseEntity<DefaultResponse> add(@RequestBody @Valid AddBookDTO request) {
        addBookUseCase.execute(new AddBookUseCase.Input(request.toBook()));

        return new DefaultResponse("Book added successfully", null)
                .toResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<DefaultResponse> update(
            @PathVariable String isbn,
            @RequestBody @Valid UpdateBookDTO request
    ) {
        updateBookUseCase.execute(new UpdateBookUseCase.Input(
                request.toBook(new ISBN(isbn)
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
        return new DefaultResponse("Book found", book).toResponseEntity();
    }

    @GetMapping
    public ResponseEntity<DefaultResponse> getAll() {
        var books = getAllBooksUseCase.execute(new GetAllBooksUseCase.Input());
        return new DefaultResponse("List of books", books).toResponseEntity();
    }
}
