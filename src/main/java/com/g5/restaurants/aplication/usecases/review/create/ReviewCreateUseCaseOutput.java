package com.g5.restaurants.aplication.usecases.review.create;

import com.g5.restaurants.aplication.domain.base.BaseId;
import com.g5.restaurants.aplication.domain.review.Review;

import java.time.LocalDateTime;

public record ReviewCreateUseCaseOutput(
        BaseId id,
        BaseId restaurantId,
        String reviewerName,
        Integer rating,
        String comments,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static ReviewCreateUseCaseOutput from(final Review review) {
        return new ReviewCreateUseCaseOutput(
                review.getId(),
                review.getRestaurantId(),
                review.getReviewerName(),
                review.getRating(),
                review.getComments(),
                review.getCreatedAt(),
                review.getUpdatedAt());
    }
}
