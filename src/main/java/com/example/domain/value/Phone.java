package com.example.domain.value;

import java.util.Objects;
import java.util.regex.Pattern;

public record Phone(String number) {
    private static final Pattern PHONE_PATTERN = Pattern.compile("^[1-9]\\d{1,14}$");

    public Phone {
        Objects.requireNonNull(number);

        var normalized = getNormalized();

        var matcher = PHONE_PATTERN.matcher(normalized);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid phone number format, must be in E.164 format");
        }
    }

    String getNormalized() {
        return Objects.requireNonNull(number).replaceAll("\\D", "");
    }

    @Override
    public String toString() {
        return number;
    }
}