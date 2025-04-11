package com.example.infrastructure.controller.dto.client;

import com.example.domain.entity.Client;
import com.example.domain.value.Address;

import java.time.ZonedDateTime;

public record ClientResponse(
        String id,
        String name,
        String email,
        String phoneNumber,
        ZonedDateTime registrationDate,
        AddressResponse address
) {
    public static ClientResponse fromEntity(Client client) {
        return new ClientResponse(
                client.getId(),
                client.getName(),
                client.getEmail().toString(),
                client.getPhoneNumber().toString(),
                client.getRegistrationDate(),
                AddressResponse.fromEntity(client.getAddress())
        );
    }

    public record AddressResponse(
            String street,
            String number,
            String city,
            String state,
            String country,
            String zipCode
    ) {
        public static AddressResponse fromEntity(Address address) {
            return new AddressResponse(
                    address.street(),
                    address.number(),
                    address.city(),
                    address.state(),
                    address.country(),
                    address.zipCode()
            );
        }
    }
}
