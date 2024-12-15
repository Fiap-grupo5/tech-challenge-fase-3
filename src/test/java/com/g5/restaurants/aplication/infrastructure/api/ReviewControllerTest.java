package com.g5.restaurants.aplication.infrastructure.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.g5.restaurants.aplication.domain.base.BaseId;
import com.g5.restaurants.aplication.usecases.review.create.ReviewCreateUseCase;
import com.g5.restaurants.aplication.usecases.review.create.ReviewCreateUseCaseOutput;
import com.g5.restaurants.aplication.usecases.review.delete.ReviewDeleteUseCase;
import com.g5.restaurants.aplication.usecases.review.retrive.get.ReviewGetByIdUseCase;
import com.g5.restaurants.aplication.usecases.review.retrive.get.ReviewGetByIdUseCaseOutput;
import com.g5.restaurants.aplication.usecases.review.retrive.list.ReviewListUseCase;
import com.g5.restaurants.aplication.usecases.review.update.ReviewUpdateUseCase;
import com.g5.restaurants.aplication.usecases.review.update.ReviewUpdateUseCaseOutput;
import com.g5.restaurants.infrastructure.api.ReviewController;
import com.g5.restaurants.infrastructure.mappers.ReviewMapper;
import com.g5.review.model.CreateReviewDTO;
import com.g5.review.model.ReviewDTO;
import com.g5.review.model.UpdateReviewDTO;

class ReviewControllerTest {

    @InjectMocks
    private ReviewController reviewController;

    @Mock
    private ReviewCreateUseCase reviewCreateUseCase;

    @Mock
    private ReviewUpdateUseCase reviewUpdateUseCase;

    @Mock
    private ReviewGetByIdUseCase reviewGetByIdUseCase;

    @Mock
    private ReviewListUseCase reviewListUseCase;

    @Mock
    private ReviewDeleteUseCase reviewDeleteUseCase;

    @Mock
    private ReviewMapper reviewMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateReview() {
        // Arrange
        var fixedId = UUID.fromString("4e609f84-b755-4a29-a7f1-ce00ace07e52");
        var restaurantId = UUID.fromString("3f1b5411-ddb9-4f59-9144-71ffad8994cd");

        var createDTO = new CreateReviewDTO()
                .restaurantId(restaurantId)
                .reviewerName("John Doe")
                .rating(5)
                .comments("Excellent!");

        var now = OffsetDateTime.now(ZoneOffset.UTC);

        var output = new ReviewCreateUseCaseOutput(
                new BaseId(fixedId.toString()),
                new BaseId(restaurantId.toString()),
                createDTO.getReviewerName(),
                createDTO.getRating(),
                createDTO.getComments(),
                now.toLocalDateTime(),
                now.toLocalDateTime()
        );

        var reviewDTO = new ReviewDTO()
                .id(fixedId)
                .restaurantId(restaurantId)
                .reviewerName("John Doe")
                .rating(5)
                .comments("Excellent!")
                .createdAt(now)
                .updatedAt(now);

        when(reviewCreateUseCase.execute(any())).thenReturn(output);
        when(reviewMapper.toDTOFromCreateOutput(output)).thenReturn(reviewDTO);

        // Act
        ResponseEntity<ReviewDTO> response = reviewController.createReview(createDTO);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody())
                .usingRecursiveComparison()
                .withComparatorForType(
                        (a, b) -> a.toInstant().equals(b.toInstant()) ? 0 : 1, OffsetDateTime.class)
                .isEqualTo(reviewDTO);
    }

    @Test
    void shouldUpdateReview() {
        // Arrange
        var id = UUID.fromString("b5434c55-a64c-4084-8567-d51102329b36");
        var restaurantId = UUID.fromString("3f1b5411-ddb9-4f59-9144-71ffad8994cd");

        var updateDTO = new UpdateReviewDTO()
                .reviewerName("Jane Doe")
                .rating(4)
                .comments("Updated Comment");

        var now = OffsetDateTime.now(ZoneOffset.UTC);

        var output = new ReviewUpdateUseCaseOutput(
                new BaseId(id.toString()),
                new BaseId(restaurantId.toString()),
                updateDTO.getReviewerName(),
                updateDTO.getRating(),
                updateDTO.getComments(),
                now.toLocalDateTime(),
                now.toLocalDateTime()
        );

        var reviewDTO = new ReviewDTO()
                .id(id)
                .restaurantId(restaurantId)
                .reviewerName("Jane Doe")
                .rating(4)
                .comments("Updated Comment")
                .createdAt(now)
                .updatedAt(now);

        when(reviewUpdateUseCase.execute(any())).thenReturn(output);
        when(reviewMapper.toDTOFromUpdateOutput(output)).thenReturn(reviewDTO);

        // Act
        ResponseEntity<ReviewDTO> response = reviewController.updateReview(id.toString(), updateDTO);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody())
                .usingRecursiveComparison()
                .withComparatorForType(
                        (a, b) -> a.toInstant().equals(b.toInstant()) ? 0 : 1, OffsetDateTime.class)
                .isEqualTo(reviewDTO);
    }

    @Test
    void shouldFindReviewById() {
        // Arrange
        var id = UUID.fromString("fd7ae989-0be9-4e05-a0ab-4eb49836ea2d");
        var restaurantId = UUID.fromString("3f1b5411-ddb9-4f59-9144-71ffad8994cd");

        var now = OffsetDateTime.now(ZoneOffset.UTC);

        var output = new ReviewGetByIdUseCaseOutput(
                new BaseId(id.toString()),
                new BaseId(restaurantId.toString()),
                "John Doe",
                5,
                "Good review!",
                now.toLocalDateTime(),
                now.toLocalDateTime()
        );

        var reviewDTO = new ReviewDTO()
                .id(id)
                .restaurantId(restaurantId)
                .reviewerName("John Doe")
                .rating(5)
                .comments("Good review!")
                .createdAt(now)
                .updatedAt(now);

        when(reviewGetByIdUseCase.execute(id.toString())).thenReturn(output);
        when(reviewMapper.toDTOFromGetByIdOutput(output)).thenReturn(reviewDTO);

        // Act
        ResponseEntity<ReviewDTO> response = reviewController.findReviewById(id.toString());

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody())
                .usingRecursiveComparison()
                .withComparatorForType(
                        (a, b) -> a.toInstant().equals(b.toInstant()) ? 0 : 1, OffsetDateTime.class)
                .isEqualTo(reviewDTO);
    }
}
