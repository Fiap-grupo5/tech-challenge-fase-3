package com.g5.restaurants.models;

public enum ReservationStatus {
    CONFIRMED("CONFIRMED"),
    CANCELLED("CANCELLED");

    private final String type;

    ReservationStatus(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
