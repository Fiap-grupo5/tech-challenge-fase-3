package com.example.cleanarchitecture.ands.core.usecases;

import com.example.cleanarchitecture.ands.core.domain.entities.Restaurant;
import com.example.cleanarchitecture.ands.core.repositories.RestaurantRepository;

public class CreateRestaurantUseCase {

  private final RestaurantRepository repository;

  public CreateRestaurantUseCase(RestaurantRepository repository) {
    this.repository = repository;
  }

  public void execute(Restaurant restaurant) {
    repository.save(restaurant);
  }
}