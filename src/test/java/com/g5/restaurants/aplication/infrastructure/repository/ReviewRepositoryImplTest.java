package com.g5.restaurants.aplication.infrastructure.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import com.g5.restaurants.aplication.domain.base.BaseId;
import com.g5.restaurants.aplication.domain.review.Review;
import com.g5.restaurants.infrastructure.persistence.repositories.ReviewMongoRepository;
import com.g5.restaurants.infrastructure.repositories.ReviewRepositoryImpl;

@DataMongoTest
class ReviewRepositoryImplTest {

    @Autowired
    private ReviewMongoRepository reviewMongoRepository;

    private ReviewRepositoryImpl reviewRepository;

    @BeforeEach
    void setUp() {
        reviewRepository = new ReviewRepositoryImpl(reviewMongoRepository);
        reviewMongoRepository.deleteAll(); // Limpa a base antes de cada teste
    }

    @Test
    void shouldCreateReview() {
        // Arrange
        Review review = createSampleReview();

        // Act
        Review savedReview = reviewRepository.create(review);

        // Assert
        assertThat(savedReview).isNotNull();
        assertThat(savedReview.getId()).isEqualTo(review.getId());
        assertThat(savedReview.getReviewerName()).isEqualTo("John Doe");
    }

    @Test
    void shouldFindReviewById() {
        // Arrange
        Review review = createSampleReview();
        reviewRepository.create(review);

        // Act
        Optional<Review> foundReview = reviewRepository.findById(review.getId().toString());

        // Assert
        assertThat(foundReview).isPresent();
        assertThat(foundReview.get().getReviewerName()).isEqualTo("John Doe");
    }

    @Test
    void shouldFindAllReviews() {
        // Arrange
        Review review1 = createSampleReview();
        Review review2 = createSampleReview("Jane Doe", 4, BaseId.generate());
        reviewRepository.create(review1);
        reviewRepository.create(review2);

        // Act
        List<Review> reviews = reviewRepository.findAll();

        // Assert
        assertThat(reviews).hasSize(2);
        assertThat(reviews).extracting("reviewerName").contains("John Doe", "Jane Doe");
    }

    @Test
    void shouldFindReviewsByRestaurantId() {
        // Arrange
        var restaurantId = BaseId.generate();
        Review review1 = createSampleReview("John Doe", 5, restaurantId);
        Review review2 = createSampleReview("Jane Doe", 4, restaurantId);
        reviewRepository.create(review1);
        reviewRepository.create(review2);
    
        // Act
        List<Review> reviews = reviewRepository.findByRestaurantId(restaurantId.toString());
    
        // Assert
        assertThat(reviews).hasSize(2);
        assertThat(reviews).extracting("reviewerName").contains("John Doe", "Jane Doe");
    }

    @Test
    void shouldUpdateReview() {
        // Arrange
        Review review = createSampleReview();
        reviewRepository.create(review);

        review.update("Updated Reviewer", 5, "Updated Comments");

        // Act
        Review updatedReview = reviewRepository.update(review);

        // Assert
        assertThat(updatedReview.getReviewerName()).isEqualTo("Updated Reviewer");
        assertThat(updatedReview.getComments()).isEqualTo("Updated Comments");
        assertThat(updatedReview.getRating()).isEqualTo(5);
    }

    @Test
    void shouldDeleteReviewById() {
        // Arrange
        Review review = createSampleReview();
        reviewRepository.create(review);

        // Act
        reviewRepository.delete(review.getId().toString());

        // Assert
        Optional<Review> deletedReview = reviewRepository.findById(review.getId().toString());
        assertThat(deletedReview).isEmpty();
    }

    private Review createSampleReview() {
        return createSampleReview("John Doe", 5, BaseId.generate());
    }

    private Review createSampleReview(String reviewerName, int rating, BaseId restaurantId) {
        return Review.newReview(
                restaurantId,
                reviewerName,
                rating,
                "Great experience!"
        );
    }
}
