package com.g5.restaurants.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.g5.restaurants.core.repositories.RestaurantRepository;
import com.g5.restaurants.core.usecases.CreateRestaurantUseCase;
import com.g5.restaurants.core.usecases.SearchRestaurantUseCase;

@Configuration
public class UseCaseConfig {

  @Bean
  public CreateRestaurantUseCase createRestaurantUseCase(RestaurantRepository repository) {
    return new CreateRestaurantUseCase(repository);
  }

  @Bean
  public SearchRestaurantUseCase searchRestaurantsUseCase(RestaurantRepository repository) {
    return new SearchRestaurantUseCase(repository);
  }
}