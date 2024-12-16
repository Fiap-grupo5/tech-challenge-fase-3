package com.g5.restaurants.aplication.usecases.review.retrieve.list.byRestaurantId;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.g5.restaurants.aplication.domain.base.BaseId;
import com.g5.restaurants.aplication.domain.review.Review;
import com.g5.restaurants.aplication.repositories.ReviewRepository;

@ExtendWith(MockitoExtension.class)
class DefaultReviewListByRestaurantIdUseCaseTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private DefaultReviewListByRestaurantIdUseCase useCase;

    @Test
    void shouldListReviewsByRestaurantId() {
        // Arrange
        var restaurantId = BaseId.generate();
        var reviewId = BaseId.generate();
        var review = new Review(
            reviewId,
            restaurantId,
            "John Doe",
            5,
            "Great",
            LocalDateTime.of(2024,12,1,14,0),
            LocalDateTime.of(2024,12,1,15,0)
        );
        when(reviewRepository.findByRestaurantId(anyString())).thenReturn(List.of(review));

        var input = new ReviewListByRestaurantIdUseCaseInput(restaurantId.value());

        // Act
        var outputs = useCase.execute(input);

        // Assert
        assertThat(outputs).isNotNull().hasSize(1);
        var output = outputs.get(0);
        assertThat(output.id()).isEqualTo(reviewId);
        assertThat(output.restaurantId()).isEqualTo(restaurantId);
        assertThat(output.reviewerName()).isEqualTo("John Doe");
        assertThat(output.rating()).isEqualTo(5);
        assertThat(output.comments()).isEqualTo("Great");
        assertThat(output.createdAt()).isEqualTo(LocalDateTime.of(2024,12,1,14,0));
        assertThat(output.updatedAt()).isEqualTo(LocalDateTime.of(2024,12,1,15,0));
    }
}
