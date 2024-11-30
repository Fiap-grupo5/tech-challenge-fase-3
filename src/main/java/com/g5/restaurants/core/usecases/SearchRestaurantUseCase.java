package com.example.cleanarchitecture.ands.core.usecases;

import java.util.List;

import com.example.cleanarchitecture.ands.core.domain.entities.Restaurant;
import com.example.cleanarchitecture.ands.core.repositories.RestaurantRepository;
import com.example.cleanarchitecture.ands.core.enums.CuisineType;

public class SearchRestaurantUseCase {
  private final RestaurantRepository restaurantRepository;

  public SearchRestaurantUseCase(RestaurantRepository restaurantRepository) {
    this.restaurantRepository = restaurantRepository;
  }

  public List<Restaurant> execute(String name, CuisineType cuisineType, Double latitude, Double longitude) {
    return restaurantRepository.findByNameAndCuisineTypeAndLocation(name, cuisineType, latitude, longitude);
  }
}
