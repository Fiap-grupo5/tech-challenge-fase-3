package com.g5.restaurants.aplication.repositories;

import com.g5.restaurants.aplication.domain.restaurant.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository {
    Restaurant create(Restaurant restaurant);
    Restaurant update(Restaurant restaurant);
    List<Restaurant> findAll();
    Optional<Restaurant> findById(String id);
    void delete(String id);
}
