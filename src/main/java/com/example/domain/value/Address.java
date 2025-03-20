package com.example.domain.value;

import java.util.Objects;
import java.util.regex.Pattern;

public record Address(String street, String number, String city, String state, String zipCode) {
    private static final Pattern CEP_PATTERN = Pattern.compile("\\d{5}-\\d{3}");

    public Address(String street, String number, String city, String state, String zipCode) {
        Objects.requireNonNull(street, "Street cannot be null");
        Objects.requireNonNull(number, "Number cannot be null");
        Objects.requireNonNull(city, "City cannot be null");
        Objects.requireNonNull(state, "State cannot be null");
        Objects.requireNonNull(zipCode, "Postal Code (CEP) cannot be null");

        this.street = street.trim();
        this.number = number.trim();
        this.city = city.trim();
        this.state = state.trim();
        this.zipCode = zipCode.trim();

        if (this.street.isEmpty() || this.city.isEmpty() || this.state.isEmpty()) {
            throw new IllegalArgumentException("Street, City and State cannot be empty");
        }

        if (!isValidCEP(this.zipCode)) {
            throw new IllegalArgumentException("Invalid CEP format: " + zipCode);
        }
    }

    private boolean isValidCEP(String cep) {
        return CEP_PATTERN.matcher(cep).matches();
    }

    public String getFullAddress() {
        return String.format("%s, %s - %s, %s, %s", street, number, city, state, zipCode);
    }

    @Override
    public String toString() {
        return getFullAddress();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return street.equals(address.street) &&
                number.equals(address.number) &&
                city.equals(address.city) &&
                state.equals(address.state) &&
                zipCode.equals(address.zipCode);
    }

}
