package com.g5.restaurants.aplication.usecases.restaurant.create;

import com.g5.restaurant.model.RestaurantDTO;

import java.time.LocalTime;

public record RestaurantCreateUseCaseInput(String name, Integer numberOfTables, String address, String city, String state, RestaurantDTO.TypeEnum type, LocalTime openedAt, LocalTime closedAt) {
}
