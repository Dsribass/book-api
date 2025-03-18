package com.example.domain.entity;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

public class Client {
    private final String id;
    private final String name;
    private final String email;
    private final String phoneNumber;
    private final String address;
    private final ZonedDateTime registrationDate;
    private Boolean active;

    public Client(String id,
                  String name,
                  String email,
                  String phoneNumber,
                  String address,
                  ZonedDateTime registrationDate,
                  Boolean active
    ) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(name);
        Objects.requireNonNull(email);
        Objects.requireNonNull(phoneNumber);
        Objects.requireNonNull(address);
        Objects.requireNonNull(registrationDate);
        Objects.requireNonNull(active);

        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.registrationDate = registrationDate;
        this.active = active;
    }

    public Client(
            String name,
            String email,
            String phoneNumber,
            String address,
            ZonedDateTime registrationDate,
            Boolean active
    ) {
        this(UUID.randomUUID().toString(), name, email, phoneNumber, address, registrationDate, active);
    }

    public void activate() {
        active = true;
    }

    public void deactivate() {
        active = false;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public ZonedDateTime getRegistrationDate() {
        return registrationDate;
    }

    public Boolean getActive() {
        return active;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(name, client.name) && Objects.equals(email, client.email) && Objects.equals(phoneNumber, client.phoneNumber) && Objects.equals(address, client.address) && Objects.equals(registrationDate, client.registrationDate) && Objects.equals(active, client.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, phoneNumber, address, registrationDate, active);
    }
}
