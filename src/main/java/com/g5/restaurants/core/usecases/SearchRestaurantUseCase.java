package com.g5.restaurants.core.usecases;

import java.util.List;

import com.g5.restaurants.core.domain.entities.Restaurant;
import com.g5.restaurants.core.enums.CuisineType;
import com.g5.restaurants.core.repositories.RestaurantRepository;


public class SearchRestaurantUseCase {
  private final RestaurantRepository restaurantRepository;

  public SearchRestaurantUseCase(RestaurantRepository restaurantRepository) {
    this.restaurantRepository = restaurantRepository;
  }

  public List<Restaurant> execute(String name, CuisineType cuisineType, Double latitude, Double longitude) {
    return restaurantRepository.findByNameAndCuisineTypeAndLocation(name, cuisineType, latitude, longitude);
  }
}
