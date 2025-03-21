package com.example.infrastructure.controller.dto.client;

import com.example.domain.entity.Client;
import com.example.domain.value.Address;

public record ClientResponse(
        String id,
        String name,
        String email,
        String phoneNumber,
        AddressResponse address
) {
    public static ClientResponse fromEntity(Client client) {
        return new ClientResponse(
                client.getId(),
                client.getName(),
                client.getEmail().toString(),
                client.getPhoneNumber().toString(),
                AddressResponse.fromEntity(client.getAddress())
        );
    }

    public record AddressResponse(
            String street,
            String number,
            String city,
            String state,
            String zipCode
    ) {
        public static AddressResponse fromEntity(Address address) {
            return new AddressResponse(
                    address.street(),
                    address.number(),
                    address.city(),
                    address.state(),
                    address.zipCode()
            );
        }
    }
}
