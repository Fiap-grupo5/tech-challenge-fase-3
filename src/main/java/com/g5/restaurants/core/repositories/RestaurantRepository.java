package com.example.cleanarchitecture.ands.core.repositories;

import com.example.cleanarchitecture.ands.core.domain.entities.Restaurant;
import com.example.cleanarchitecture.ands.core.enums.CuisineType;
import java.util.List;

public interface RestaurantRepository {
  void save(Restaurant restaurant);

  List<Restaurant> findByNameAndCuisineTypeAndLocation(
      String name,
      CuisineType cuisineType,
      Double latitude,
      Double longitude);
}