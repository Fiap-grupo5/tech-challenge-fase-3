package com.g5.restaurants.core.repositories;

import java.util.List;

import com.g5.restaurants.core.domain.entities.Restaurant;
import com.g5.restaurants.core.enums.CuisineType;

public interface RestaurantRepository {
  void save(Restaurant restaurant);

  List<Restaurant> findByNameAndCuisineTypeAndLocation(
      String name,
      CuisineType cuisineType,
      Double latitude,
      Double longitude);
}