package com.example.infrastructure.controller.dto.client;

import com.example.domain.entity.Client;
import com.example.domain.value.Address;
import com.example.domain.value.Email;
import com.example.domain.value.Phone;
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

        @NotNull(message = "Address is required")
        ClientRequest.AddressRequest address
) {
    public Client toEntity() {
        return new Client(
                name,
                new Email(email),
                new Phone(phoneNumber),
                address.toEntity(),
                true);
    }

    public Client toEntity(String id) {
        return new Client(
                id,
                name,
                new Email(email),
                new Phone(phoneNumber),
                address.toEntity(),
                ZonedDateTime.now(),
                true);
    }

    public record AddressRequest(
            @NotBlank(message = "Street is required")
            String street,
            @NotBlank(message = "Number is required")
            String number,
            @NotBlank(message = "City is required")
            String city,
            @NotBlank(message = "State is required")
            String state,
            @NotBlank(message = "Country is required")
            String country,
            @NotBlank(message = "Zip code is required")
            String zipCode
    ) {
        public Address toEntity() {
            return new Address(street, number, city, state, country, zipCode);
        }
    }

}
