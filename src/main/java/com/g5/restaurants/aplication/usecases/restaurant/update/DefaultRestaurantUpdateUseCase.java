package com.g5.restaurants.aplication.usecases.restaurant.update;

import com.g5.restaurants.aplication.domain.restaurant.Restaurant;
import com.g5.restaurants.aplication.exceptions.CommonException;
import com.g5.restaurants.aplication.repositories.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class DefaultRestaurantUpdateUseCase extends RestaurantUpdateUseCase {
    private final RestaurantRepository restaurantRepository;
    @Override
    public RestaurantUpdateUseCaseOutput execute(RestaurantUpdateUseCaseInput input) {
        final var id = input.id();
        Restaurant restaurant = restaurantRepository
                        .findById(id)
                        .orElseThrow(
                                () -> new CommonException("Restaurant with ID %s not found.".formatted(id), HttpStatus.NOT_FOUND));
        restaurant.update(
                input.name(),
                input.numberOfTables(),
                input.address(),
                input.city(),
                input.state(),
                input.type(),
                input.openedAt(),
                input.closedAt()
        );
        return RestaurantUpdateUseCaseOutput.from(restaurantRepository.update(restaurant));
    }
}
