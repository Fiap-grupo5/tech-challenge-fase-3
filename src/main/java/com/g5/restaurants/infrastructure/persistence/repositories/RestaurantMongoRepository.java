package com.g5.restaurants.infrastructure.persistence.repositories;

import com.g5.restaurants.infrastructure.persistence.entities.RestaurantEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantMongoRepository extends MongoRepository<RestaurantEntity, String> {
    List<RestaurantEntity> findAll();
}
