package com.g5.restaurants.aplication.usecases.restaurant.retrive.list;

import com.g5.restaurants.aplication.repositories.RestaurantRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DefaultRestaurantListUseCase extends RestaurantListUseCase {
    private final RestaurantRepository restaurantRepository;

    @Override
    public List<RestaurantListUseCaseOutput> execute() {
        return restaurantRepository
                .findAll()
                .stream()
                .map(RestaurantListUseCaseOutput::from)
                .collect(Collectors.toList());
    }
}
