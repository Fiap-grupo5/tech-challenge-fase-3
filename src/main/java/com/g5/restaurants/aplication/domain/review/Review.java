package com.g5.restaurants.aplication.domain.review;

import java.time.LocalDateTime;
import java.util.Objects;

import com.g5.restaurants.aplication.domain.base.BaseId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    private BaseId id;
    private BaseId restaurantId;
    private String reviewerName;
    private Integer rating;
    private String comments;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static Review newReview(BaseId restaurantId, String reviewerName, Integer rating, String comments) {
        if (rating == null || rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
        var id = BaseId.generate();
        var now = LocalDateTime.now();
        return new Review(
                id,
                restaurantId,
                reviewerName,
                rating,
                comments,
                now,
                now
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(id, review.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void update(String reviewerName, Integer rating, String comments) {
        this.reviewerName = reviewerName;
        this.rating = rating;
        this.comments = comments;
        this.updatedAt = LocalDateTime.now();
    }
}
