package com.g5.restaurants.core.domain.entities;

import java.time.LocalTime;

import com.g5.restaurants.core.enums.CuisineType;

public record Restaurant(
    String id,
    String name,
    CuisineType cuisineType,
    String address,
    Double latitude,
    Double longitude,
    LocalTime openingHours,
    LocalTime closingHours,
    int capacity
) {}
