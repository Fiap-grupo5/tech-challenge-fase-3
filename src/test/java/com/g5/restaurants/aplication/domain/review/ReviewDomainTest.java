package com.g5.restaurants.aplication.domain.review;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.g5.restaurants.aplication.domain.base.BaseId;

class ReviewDomainTest {

    @Test
    void shouldCreateNewReview() {
        // Arrange
        var restaurantId = BaseId.generate();
        var reviewerName = "John Doe";
        var rating = 5;
        var comments = "Excellent food and great service!";

        // Act
        var review = Review.newReview(restaurantId, reviewerName, rating, comments);

        // Assert
        assertThat(review).isNotNull();
        assertThat(review.getId()).isNotNull();
        assertThat(review.getRestaurantId()).isEqualTo(restaurantId);
        assertThat(review.getReviewerName()).isEqualTo(reviewerName);
        assertThat(review.getRating()).isEqualTo(rating);
        assertThat(review.getComments()).isEqualTo(comments);
        assertThat(review.getCreatedAt()).isNotNull();
        assertThat(review.getUpdatedAt()).isNotNull();
    }

    @Test
    void shouldUpdateReview() {
        // Arrange
        var review = new Review(
            BaseId.generate(),
            BaseId.generate(),
            "Jane Doe",
            4,
            "Great food!",
            LocalDateTime.of(2024, 1, 1, 12, 0),
            LocalDateTime.of(2024, 1, 1, 12, 0)
        );

        var newReviewerName = "John Smith";
        var newRating = 3;
        var newComments = "Good food, but the service could improve.";

        // Act
        review.update(newReviewerName, newRating, newComments);

        // Assert
        assertThat(review.getReviewerName()).isEqualTo(newReviewerName);
        assertThat(review.getRating()).isEqualTo(newRating);
        assertThat(review.getComments()).isEqualTo(newComments);
        assertThat(review.getUpdatedAt()).isAfter(review.getCreatedAt());
    }

    @Test
    void shouldThrowExceptionWhenRatingIsInvalid() {
        // Arrange
        var restaurantId = BaseId.generate();
        var reviewerName = "John Doe";
        var invalidRating = 6;
        var comments = "Great service!";

        // Act & Assert
        assertThatThrownBy(() -> Review.newReview(restaurantId, reviewerName, invalidRating, comments))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Rating must be between 1 and 5");
    }

    @Test
    void shouldVerifyEqualityBasedOnId() {
        // Arrange
        var id = BaseId.generate();
        var review1 = new Review(
            id,
            BaseId.generate(),
            "Jane Doe",
            4,
            "Great food!",
            LocalDateTime.now(),
            LocalDateTime.now()
        );

        var review2 = new Review(
            id,
            BaseId.generate(),
            "John Smith",
            5,
            "Excellent experience!",
            LocalDateTime.now(),
            LocalDateTime.now()
        );

        // Assert
        assertThat(review1).isEqualTo(review2);
        assertThat(review1.hashCode()).isEqualTo(review2.hashCode());
    }

    @Test
    void shouldVerifyToStringMethod() {
        // Arrange
        var review = new Review(
            BaseId.generate(),
            BaseId.generate(),
            "Jane Doe",
            5,
            "Amazing experience!",
            LocalDateTime.of(2024, 12, 1, 14, 0),
            LocalDateTime.of(2024, 12, 1, 14, 0)
        );

        // Act
        var result = review.toString();

        // Assert
        assertThat(result).contains("Jane Doe");
        assertThat(result).contains("5");
        assertThat(result).contains("Amazing experience!");
    }
}
