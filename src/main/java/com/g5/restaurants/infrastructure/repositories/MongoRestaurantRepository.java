package com.g5.restaurants.infrastructure.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.g5.restaurants.core.domain.entities.Restaurant;

@Repository
public interface MongoRestaurantRepository extends MongoRepository<Restaurant, String> {

}