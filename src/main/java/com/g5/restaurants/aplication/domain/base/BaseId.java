package com.g5.restaurants.aplication.domain.base;

import java.util.UUID;

public record BaseId(String value) {

    public BaseId {
        if (value == null) {
            throw new IllegalArgumentException("Category id cannot be null");
        }
    }

    public static BaseId generate() {
        return new BaseId(UUID.randomUUID().toString());
    }

    public static BaseId from(final String value) {
        try {
            return new BaseId(UUID.fromString(value).toString());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid id");
        }
    }

    @Override
    public String toString() {
        return value;
    }

}
