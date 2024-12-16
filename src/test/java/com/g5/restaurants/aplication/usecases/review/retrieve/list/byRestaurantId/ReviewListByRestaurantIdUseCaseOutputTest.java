package com.g5.restaurants.aplication.usecases.review.retrieve.list.byRestaurantId;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.g5.restaurants.aplication.domain.base.BaseId;
import com.g5.restaurants.aplication.domain.review.Review;

class ReviewListByRestaurantIdUseCaseOutputTest {

    @Test
    void shouldCreateOutputFromReview() {
        // Arrange
        var reviewId = BaseId.generate();
        var restaurantId = BaseId.generate();
        var reviewerName = "John Doe";
        var rating = 5;
        var comments = "Excellent!";
        var createdAt = LocalDateTime.of(2024, 12, 1, 14, 0);
        var updatedAt = LocalDateTime.of(2024, 12, 1, 15, 0);
        var review = new Review(reviewId, restaurantId, reviewerName, rating, comments, createdAt, updatedAt);

        // Act
        var output = ReviewListByRestaurantIdUseCaseOutput.from(review);

        // Assert
        assertThat(output).isNotNull();
        assertThat(output.id()).isEqualTo(reviewId);
        assertThat(output.restaurantId()).isEqualTo(restaurantId);
        assertThat(output.reviewerName()).isEqualTo(reviewerName);
        assertThat(output.rating()).isEqualTo(rating);
        assertThat(output.comments()).isEqualTo(comments);
        assertThat(output.createdAt()).isEqualTo(createdAt);
        assertThat(output.updatedAt()).isEqualTo(updatedAt);
    }
}
