package com.g5.restaurants.aplication.usecases.restaurant.delete;

import com.g5.restaurants.aplication.exceptions.CommonException;
import com.g5.restaurants.aplication.repositories.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class DefaultRestaurantDeleteUseCase extends RestaurantDeleteUseCase {
    private final RestaurantRepository restaurantRepository;

    @Override
    public void execute(String input) {
        restaurantRepository.findById(input).orElseThrow(() -> new CommonException("Restaurant with ID %s not found.".formatted(input), HttpStatus.NOT_FOUND));
        restaurantRepository.delete(input);
    }
}
