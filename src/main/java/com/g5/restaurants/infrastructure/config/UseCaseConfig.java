package com.example.cleanarchitecture.ands.infrastructure.config;

import com.example.cleanarchitecture.ands.core.repositories.RestaurantRepository;
import com.example.cleanarchitecture.ands.core.usecases.CreateRestaurantUseCase;
import com.example.cleanarchitecture.ands.core.usecases.SearchRestaurantUseCase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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