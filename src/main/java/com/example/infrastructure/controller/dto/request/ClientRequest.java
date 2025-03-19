package com.example.infrastructure.controller.dto.request;

import com.example.domain.entity.Client;
import com.example.domain.entity.Email;
import com.example.domain.entity.Phone;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.ZonedDateTime;

public record ClientRequest(
        @NotBlank(message = "Name is required")
        String name,
        @NotNull(message = "Email is required")
        String email,
        @NotNull(message = "Phone number is required")
        String phoneNumber,
        @NotBlank(message = "Address is required")
        String address
) {
    public Client toClient() {
        return new Client(
                name,
                new Email(email),
                new Phone(phoneNumber),
                address,
                true);
    }

    public Client toClient(String id) {
        return new Client(
                id,
                name,
                new Email(email),
                new Phone(phoneNumber),
                address,
                ZonedDateTime.now(),
                true);
    }
}
