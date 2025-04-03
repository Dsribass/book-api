package com.example.domain.value;

import java.util.Objects;
import java.util.regex.Pattern;

public class Phone {
    private static final Pattern PHONE_PATTERN = Pattern.compile("^[1-9]\\d{1,14}$");

    private final String number;

    public Phone(String number) {
        Objects.requireNonNull(number);

        var normalized = normalize(number);

        var matcher = PHONE_PATTERN.matcher(normalized);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid phone number format, must be in E.164 format");
        }

        this.number = normalized;
    }

    private String normalize(String number) {
        return number.replaceAll("\\D", "");
    }

    @Override
    public String toString() {
        return number;
    }
}