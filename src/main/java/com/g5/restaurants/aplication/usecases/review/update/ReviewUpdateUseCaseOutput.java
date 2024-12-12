package com.g5.restaurants.aplication.usecases.review.update;

import com.g5.restaurants.aplication.domain.base.BaseId;
import com.g5.restaurants.aplication.domain.review.Review;

import java.time.LocalDateTime;

public record ReviewUpdateUseCaseOutput(
        BaseId id,
        BaseId restaurantId,
        String reviewerName,
        Integer rating,
        String comments,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static ReviewUpdateUseCaseOutput from(final Review review) {
        return new ReviewUpdateUseCaseOutput(
                review.getId(),
                review.getRestaurantId(),
                review.getReviewerName(),
                review.getRating(),
                review.getComments(),
                review.getCreatedAt(),
                review.getUpdatedAt());
    }
}
