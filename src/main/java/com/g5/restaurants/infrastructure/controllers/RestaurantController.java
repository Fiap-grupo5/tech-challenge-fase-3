package com.example.cleanarchitecture.ands.infrastructure.controllers;

import com.example.cleanarchitecture.ands.core.domain.entities.Restaurant;
import com.example.cleanarchitecture.ands.core.enums.CuisineType;
import com.example.cleanarchitecture.ands.core.usecases.CreateRestaurantUseCase;
import com.example.cleanarchitecture.ands.core.usecases.SearchRestaurantUseCase;

import org.springframework.web.bind.annotation.*;

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