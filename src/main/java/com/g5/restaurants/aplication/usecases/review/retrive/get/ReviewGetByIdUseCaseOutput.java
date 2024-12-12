package com.g5.restaurants.aplication.usecases.review.retrive.get;

import com.g5.restaurants.aplication.domain.base.BaseId;
import com.g5.restaurants.aplication.domain.review.Review;

import java.time.LocalDateTime;

public record ReviewGetByIdUseCaseOutput(BaseId id,
                                         BaseId restaurantId,
                                         String reviewerName,
                                         Integer rating,
                                         String comments,
                                         LocalDateTime createdAt,
                                         LocalDateTime updatedAt) {
    public static ReviewGetByIdUseCaseOutput from(final Review review) {
        return new ReviewGetByIdUseCaseOutput(
                review.getId(),
                review.getRestaurantId(),
                review.getReviewerName(),
                review.getRating(),
                review.getComments(),
                review.getCreatedAt(),
                review.getUpdatedAt());
    }
}
