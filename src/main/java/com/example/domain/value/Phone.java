package com.example.domain.value;

import java.util.Objects;
import java.util.regex.Pattern;

public final class Phone {
    private static final Pattern PHONE_PATTERN = Pattern.compile("^(\\+\\d{1,3})(\\d{6,14})$");

    private final String countryCode;
    private final String number;

    public Phone(String fullNumber) {
        Objects.requireNonNull(fullNumber);

        var matcher = PHONE_PATTERN.matcher(fullNumber);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid phone number format, must be in E.164 format");
        }
        this.countryCode = matcher.group(1);
        this.number = matcher.group(2);
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getNumber() {
        return number;
    }

    public String getFullNumber() {
        return countryCode + number;
    }

    @Override
    public String toString() {
        return getFullNumber();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Phone phone = (Phone) o;
        return countryCode.equals(phone.countryCode) && number.equals(phone.number);
    }

    @Override
    public int hashCode() {
        int result = countryCode.hashCode();
        result = 31 * result + number.hashCode();
        return result;
    }
}