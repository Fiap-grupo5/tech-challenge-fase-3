package com.g5.restaurants.infrastructure.repositories;

import com.g5.restaurants.aplication.domain.restaurant.Restaurant;
import com.g5.restaurants.aplication.repositories.RestaurantRepository;
import com.g5.restaurants.infrastructure.persistence.entities.RestaurantEntity;
import com.g5.restaurants.infrastructure.persistence.repositories.RestaurantMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantRepositoryImpl implements RestaurantRepository {
    private final RestaurantMongoRepository restaurantMongoRepository;

    @Override
    public Restaurant create(Restaurant restaurant) {
        return save(restaurant);
    }

    @Override
    public Restaurant update(Restaurant restaurant) {
        return save(restaurant);
    }

    @Override
    public List<Restaurant> findAll() {
        final var pageResult = restaurantMongoRepository.findAll();

        return pageResult.stream().map(RestaurantEntity::toRestaurant).toList();
    }

    @Override
    public Optional<Restaurant> findById(String id) {
        return restaurantMongoRepository.findById(id).map(RestaurantEntity::toRestaurant);
    }

    @Override
    public void delete(String id) {
        restaurantMongoRepository.deleteById(id);
    }

    private Restaurant save(final Restaurant restaurant) {
        return restaurantMongoRepository.save(RestaurantEntity.of(restaurant)).toRestaurant();
    }
}
