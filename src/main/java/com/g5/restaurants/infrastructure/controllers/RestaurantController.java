package com.g5.restaurants.infrastructure.controllers;

import org.springframework.web.bind.annotation.*;

import com.g5.restaurants.core.domain.entities.Restaurant;
import com.g5.restaurants.core.enums.CuisineType;
import com.g5.restaurants.core.usecases.CreateRestaurantUseCase;
import com.g5.restaurants.core.usecases.SearchRestaurantUseCase;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

  private final CreateRestaurantUseCase createRestaurantUseCase;
  private final SearchRestaurantUseCase searchRestaurantUseCase;

  public RestaurantController(CreateRestaurantUseCase createRestaurantUseCase,
      SearchRestaurantUseCase searchRestaurantUseCase) {
    this.createRestaurantUseCase = createRestaurantUseCase;
    this.searchRestaurantUseCase = searchRestaurantUseCase;
  }

  @PostMapping
  public void cadastrar(@RequestBody Restaurant restaurant) {
    createRestaurantUseCase.execute(restaurant);
  }

  @GetMapping
  public List<Restaurant> buscar(
      @RequestParam(required = false) String name,
      @RequestParam(required = false) CuisineType cuisineType,
      @RequestParam(required = false) Double latitude,
      @RequestParam(required = false) Double longitude) {
    return searchRestaurantUseCase.execute(name, cuisineType, latitude, longitude);
  }
}