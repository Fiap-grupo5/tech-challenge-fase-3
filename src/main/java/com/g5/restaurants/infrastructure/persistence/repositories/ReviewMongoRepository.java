package com.g5.restaurants.infrastructure.persistence.repositories;

import com.g5.restaurants.infrastructure.persistence.entities.ReviewEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewMongoRepository extends MongoRepository<ReviewEntity, String> {
    @Query("{'restaurantId': ?0}")
    List<ReviewEntity> findByRestaurantId(String restaurantId);
}
