package com.example.domain.entity;

import com.example.domain.value.Address;
import com.example.domain.value.Email;
import com.example.domain.value.Phone;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

public class Client {
    private final String id;
    private final String name;
    private final Email email;
    private final Phone phoneNumber;
    private final Address address;
    private final ZonedDateTime registrationDate;
    private Boolean active;

    public Client(String id,
                  String name,
                  Email email,
                  Phone phoneNumber,
                  Address address,
                  ZonedDateTime registrationDate,
                  Boolean active
    ) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(email);
        Objects.requireNonNull(phoneNumber);
        Objects.requireNonNull(address);
        Objects.requireNonNull(registrationDate);
        Objects.requireNonNull(active);

        this.id = id != null ? id : UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.registrationDate = registrationDate;
        this.active = active;
    }

    public Client(
            String name,
            Email email,
            Phone phoneNumber,
            Address address,
            Boolean active
    ) {
        this(UUID.randomUUID().toString(), name, email, phoneNumber, address, ZonedDateTime.now(), active);
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

    public Email getEmail() {
        return email;
    }

    public Phone getPhoneNumber() {
        return phoneNumber;
    }

    public Address getAddress() {
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
