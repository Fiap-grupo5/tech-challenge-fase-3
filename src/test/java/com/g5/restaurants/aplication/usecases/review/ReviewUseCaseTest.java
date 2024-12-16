package com.g5.restaurants.aplication.usecases.review;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.g5.restaurant.model.RestaurantDTO;
import com.g5.restaurants.aplication.domain.restaurant.Restaurant;
import com.g5.restaurants.aplication.domain.review.Review;
import com.g5.restaurants.aplication.exceptions.CommonException;
import com.g5.restaurants.aplication.repositories.ReviewRepository;
import com.g5.restaurants.aplication.usecases.review.create.DefaultReviewCreateUseCase;
import com.g5.restaurants.aplication.usecases.review.create.ReviewCreateUseCaseInput;
import com.g5.restaurants.aplication.usecases.review.delete.DefaultReviewDeleteUseCase;
import com.g5.restaurants.aplication.usecases.review.retrieve.get.DefaultReviewGetByIdUseCase;
import com.g5.restaurants.aplication.usecases.review.retrieve.list.DefaultReviewListUseCase;
import com.g5.restaurants.aplication.usecases.review.update.DefaultReviewUpdateUseCase;
import com.g5.restaurants.aplication.usecases.review.update.ReviewUpdateUseCaseInput;

@ExtendWith(MockitoExtension.class)
public class ReviewUseCaseTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private DefaultReviewCreateUseCase createUseCase;

    @InjectMocks
    private DefaultReviewDeleteUseCase deleteUseCase;

    @InjectMocks
    private DefaultReviewGetByIdUseCase getByIdUseCase;

    @InjectMocks
    private DefaultReviewListUseCase listUseCase;

    @InjectMocks
    private DefaultReviewUpdateUseCase updateUseCase;

    private Restaurant createRestaurant() {
        return Restaurant.newRestaurant(
            "Tia Nicole",
            10,
            "Rua das Clarisas, 100",
            "Belo Horizonte",
            "MG",
            RestaurantDTO.TypeEnum.BRAZILIAN,
            LocalTime.of(10, 0),
            LocalTime.of(22, 0)
        );
    }

    private Review createReview() {
        var restaurant = createRestaurant();
        return Review.newReview(
            restaurant.getId(),
            "Reviewer Name",
            5,
            "Great food!"
        );
    }

    @Test
    void shouldCreateReview() {
        // Arrange
        var restaurant = createRestaurant();
        var input = new ReviewCreateUseCaseInput(
            restaurant.getId().toString(),
            "Reviewer Name",
            5,
            "Great food!"
        );

        var review = createReview();
        when(reviewRepository.create(any())).thenReturn(review);

        // Act
        var output = createUseCase.execute(input);

        // Assert
        assertThat(output).isNotNull();
        assertThat(output.reviewerName()).isEqualTo("Reviewer Name");
        verify(reviewRepository, times(1)).create(any());
    }

    @Test
    void shouldDeleteReview() {
        // Arrange
        String reviewId = "review-id";
        var review = createReview();
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(review));
        doNothing().when(reviewRepository).delete(reviewId);

        // Act
        deleteUseCase.execute(reviewId);

        // Assert
        verify(reviewRepository, times(1)).delete(reviewId);
    }

    @Test
    void shouldThrowExceptionWhenReviewNotFoundForDelete() {
        // Arrange
        String reviewId = "non-existent-id";
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> deleteUseCase.execute(reviewId))
            .isInstanceOf(CommonException.class)
            .hasMessage("Review with ID non-existent-id not found!");
    }

    @Test
    void shouldGetReviewById() {
        // Arrange
        String reviewId = "review-id";
        var review = createReview();
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(review));

        // Act
        var output = getByIdUseCase.execute(reviewId);

        // Assert
        assertThat(output).isNotNull();
        assertThat(output.reviewerName()).isEqualTo("Reviewer Name");
        verify(reviewRepository, times(1)).findById(reviewId);
    }

    @Test
    void shouldThrowExceptionWhenReviewNotFoundForGetById() {
        // Arrange
        String reviewId = "non-existent-id";
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> getByIdUseCase.execute(reviewId))
            .isInstanceOf(CommonException.class)
            .hasMessage("Review with ID non-existent-id not found!.");
    }

    @Test
    void shouldListReviews() {
        // Arrange
        var review = createReview();
        when(reviewRepository.findAll()).thenReturn(List.of(review));

        // Act
        var outputs = listUseCase.execute();

        // Assert
        assertThat(outputs).isNotEmpty();
        assertThat(outputs.get(0).reviewerName()).isEqualTo("Reviewer Name");
        verify(reviewRepository, times(1)).findAll();
    }

    @Test
    void shouldUpdateReview() {
        // Arrange
        String reviewId = "review-id";
        var input = new ReviewUpdateUseCaseInput(
            reviewId,
            "Updated Name",
            4,
            "Updated Comment"
        );

        var review = createReview();
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(review));
        when(reviewRepository.update(any())).thenReturn(review);

        // Act
        var output = updateUseCase.execute(input);

        // Assert
        assertThat(output).isNotNull();
        assertThat(output.reviewerName()).isEqualTo("Updated Name");
        verify(reviewRepository, times(1)).findById(reviewId);
        verify(reviewRepository, times(1)).update(any());
    }

    @Test
    void shouldThrowExceptionWhenReviewNotFoundForUpdate() {
        // Arrange
        String reviewId = "non-existent-id";
        var input = new ReviewUpdateUseCaseInput(
            reviewId,
            "Updated Name",
            4,
            "Updated Comment"
        );
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> updateUseCase.execute(input))
            .isInstanceOf(CommonException.class)
            .hasMessage("Review with ID non-existent-id not found!.");
    }
}
