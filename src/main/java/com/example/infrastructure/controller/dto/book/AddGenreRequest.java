package com.example.infrastructure.controller.dto.book;

import jakarta.validation.constraints.NotBlank;

public record AddGenreRequest(
        @NotBlank(message = "Genre name is required")
        String name
) {}
