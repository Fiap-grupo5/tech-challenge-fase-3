package com.g5.restaurants.aplication.usecases.restaurant.retrive.get;

import com.g5.restaurants.aplication.exceptions.CommonException;
import com.g5.restaurants.aplication.repositories.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class DefaultRestaurantGetByIdUseCase extends RestaurantGetByIdUseCase {
    private final RestaurantRepository restaurantRepository;

    @Override
    public RestaurantGetByIdUseCaseOutput execute(String input) {
        return restaurantRepository
                .findById(input)
                .map(RestaurantGetByIdUseCaseOutput::from)
                .orElseThrow(
                        () -> new CommonException("Restaurant with ID %s not found.".formatted(input), HttpStatus.NOT_FOUND));
    }
}

