package com.g5.restaurants.aplication.usecases.restaurant.create;

import com.g5.restaurants.aplication.domain.restaurant.Restaurant;
import com.g5.restaurants.aplication.repositories.RestaurantRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultRestaurantCreateUseCase extends RestaurantCreateUseCase {
    private final RestaurantRepository restaurantRepository;

    @Override
    public RestaurantCreateUseCaseOutput execute(RestaurantCreateUseCaseInput input) {
        Restaurant restaurant =
                Restaurant.newRestaurant(
                        input.name(),
                        input.numberOfTables(),
                        input.address(),
                        input.city(),
                        input.state(),
                        input.type(),
                        input.openedAt(),
                        input.closedAt()
                );

        return RestaurantCreateUseCaseOutput.from(restaurantRepository.create(restaurant));
    }
}
