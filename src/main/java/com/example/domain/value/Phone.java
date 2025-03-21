package com.example.domain.value;

import java.util.Objects;
import java.util.regex.Pattern;

public record Phone(String number) {
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+?[1-9]\\d{1,14}$");

    public Phone {
        Objects.requireNonNull(number);

        var matcher = PHONE_PATTERN.matcher(number);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid phone number format, must be in E.164 format");
        }
    }

    @Override
    public String toString() {
        return number;
    }
}