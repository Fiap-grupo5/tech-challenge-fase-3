package com.g5.restaurants.aplication.usecases.restaurant.create;

import com.g5.restaurant.model.RestaurantDTO;
import com.g5.restaurants.aplication.domain.base.BaseId;
import com.g5.restaurants.aplication.domain.restaurant.Restaurant;

import java.time.LocalTime;

public record RestaurantCreateUseCaseOutput(BaseId id, String name, Integer numberOfTables, String address, String city, String state, RestaurantDTO.TypeEnum type, LocalTime openedAt, LocalTime closedAt) {
    public static RestaurantCreateUseCaseOutput from(final Restaurant input) {
        return new RestaurantCreateUseCaseOutput(
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
