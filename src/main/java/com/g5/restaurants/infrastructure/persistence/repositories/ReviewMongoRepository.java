package com.g5.restaurants.infrastructure.persistence.repositories;

import com.g5.restaurants.infrastructure.persistence.entities.ReviewEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewMongoRepository extends MongoRepository<ReviewEntity, String> {
    List<ReviewEntity> findAll();
    @Query("{'restaurantId': ?0}")
    List<ReviewEntity> findByRestaurantId(@Param("id") String restaurantId);
}
