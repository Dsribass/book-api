package com.example.infrastructure.controller.dto;

import com.example.domain.entity.Client;
import com.example.domain.value.Email;
import com.example.domain.value.Phone;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.ZonedDateTime;

public record ClientDTO(
        @NotBlank(message = "Name is required")
        String name,

        @NotNull(message = "Email is required")
        String email,

        @NotNull(message = "Phone number is required")
        String phoneNumber,

        @NotNull(message = "Address is required")
        Address address
) {
    public Client toDomain() {
        return new Client(
                name,
                new Email(email),
                new Phone(phoneNumber),
                address.toDomain(),
                true);
    }

    public Client toDomain(String id) {
        return new Client(
                id,
                name,
                new Email(email),
                new Phone(phoneNumber),
                address.toDomain(),
                ZonedDateTime.now(),
                true);
    }

    public record Address(
            @NotBlank(message = "Street is required")
            String street,
            @NotBlank(message = "Number is required")
            String number,
            @NotBlank(message = "City is required")
            String city,
            @NotBlank(message = "State is required")
            String state,
            @NotBlank(message = "Zip code is required")
            String zipCode
    ) {
        public com.example.domain.value.Address toDomain() {
            return new com.example.domain.value.Address(street, number, city, state, zipCode);
        }
    }

}
