package com.g5.restaurants.core.usecases;

import com.g5.restaurants.core.domain.entities.Restaurant;
import com.g5.restaurants.core.repositories.RestaurantRepository;

public class CreateRestaurantUseCase {

  private final RestaurantRepository repository;

  public CreateRestaurantUseCase(RestaurantRepository repository) {
    this.repository = repository;
  }

  public void execute(Restaurant restaurant) {
    repository.save(restaurant);
  }
}