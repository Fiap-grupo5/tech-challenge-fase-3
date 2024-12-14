package com.g5.restaurants.infrastructure.persistence.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.g5.restaurants.aplication.domain.review.Review;
import com.g5.restaurants.infrastructure.mappers.ReviewMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "review")
public class ReviewEntity {
    @Id
    private String id;

    private String restaurantId;

    private String reviewerName;

    private Integer rating;

    private String comments;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;

    public static ReviewEntity of(Review review) {
        return new ReviewEntity(
          review.getId().toString(),
          review.getRestaurantId().toString(),
          review.getReviewerName(),
          review.getRating(),
          review.getComments(),
          review.getCreatedAt(),
          review.getUpdatedAt()
        );
    }

    public Review toReview() {
        return ReviewMapper.INSTANCE.toObject( this);
    }
}
