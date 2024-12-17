package com.g5.restaurants.aplication.usecases.restaurant.retrive.list;

import com.g5.restaurant.model.RestaurantDTO;
import com.g5.restaurants.aplication.domain.base.BaseId;
import com.g5.restaurants.aplication.domain.restaurant.Restaurant;

import java.time.LocalTime;

public record RestaurantListUseCaseOutput(BaseId id, String name, Integer numberOfTables, String address, String city, String state, RestaurantDTO.TypeEnum type, LocalTime openedAt, LocalTime closedAt) {
    public static RestaurantListUseCaseOutput from(final Restaurant input) {
        return new RestaurantListUseCaseOutput(
                input.getId(),
                input.getName(),
                input.getNumberOfTables(),
                input.getAddress(),
                input.getCity(),
                input.getState(),
                input.getType(),
                input.getOpenedAt(),
                input.getClosedAt()
        );
    }
}
