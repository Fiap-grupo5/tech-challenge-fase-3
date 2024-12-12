package com.g5.restaurants.aplication.domain.review;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.g5.restaurants.aplication.domain.base.BaseId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    public void update(String reviewerName, Integer rating, String comments) {
        this.reviewerName = reviewerName;
        this.rating = rating;
        this.comments = comments;
        this.updatedAt = LocalDateTime.now();
    }
}
