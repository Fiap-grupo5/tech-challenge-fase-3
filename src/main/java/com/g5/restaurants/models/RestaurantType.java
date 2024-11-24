package com.g5.restaurants.models;

public enum RestaurantType {
    JAPAN("JAPAN"),
    BRAZILIAN("BRAZILIAN"),
    LAUNCH("LAUNCH"),
    ARABIC("ARABIC"),
    ITALIAN("ITALIAN"),
    MEXICAN("MEXICAN"),
    CHINESE("CHINESE"),
    VEGAN("VEGAN");

    private final String type;

    RestaurantType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
