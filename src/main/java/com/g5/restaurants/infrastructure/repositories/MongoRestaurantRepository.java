package com.example.cleanarchitecture.ands.infrastructure.repositories;

import com.example.cleanarchitecture.ands.core.domain.entities.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoRestaurantRepository extends MongoRepository<Restaurant, String> {

}