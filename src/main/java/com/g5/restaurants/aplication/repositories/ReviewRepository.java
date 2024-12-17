package com.g5.restaurants.aplication.repositories;

import com.g5.restaurants.aplication.domain.review.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {
    Review create(Review review);
    List<Review> findAll();
    Optional<Review> findById(String id);
    List<Review> findByRestaurantId(String id);
    Review update(Review review);
    void delete(String id);
}
