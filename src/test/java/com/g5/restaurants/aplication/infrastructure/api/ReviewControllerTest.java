package com.g5.restaurants.aplication.infrastructure.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.g5.restaurants.aplication.domain.base.BaseId;
import com.g5.restaurants.aplication.usecases.review.create.ReviewCreateUseCase;
import com.g5.restaurants.aplication.usecases.review.create.ReviewCreateUseCaseOutput;
import com.g5.restaurants.aplication.usecases.review.delete.ReviewDeleteUseCase;
import com.g5.restaurants.aplication.usecases.review.retrieve.get.ReviewGetByIdUseCase;
import com.g5.restaurants.aplication.usecases.review.retrieve.get.ReviewGetByIdUseCaseOutput;
import com.g5.restaurants.aplication.usecases.review.retrieve.list.ReviewListUseCase;
import com.g5.restaurants.aplication.usecases.review.retrieve.list.ReviewListUseCaseOutput;
import com.g5.restaurants.aplication.usecases.review.retrieve.list.byRestaurantId.ReviewListByRestaurantIdUseCase;
import com.g5.restaurants.aplication.usecases.review.retrieve.list.byRestaurantId.ReviewListByRestaurantIdUseCaseInput;
import com.g5.restaurants.aplication.usecases.review.retrieve.list.byRestaurantId.ReviewListByRestaurantIdUseCaseOutput;
import com.g5.restaurants.aplication.usecases.review.update.ReviewUpdateUseCase;
import com.g5.restaurants.aplication.usecases.review.update.ReviewUpdateUseCaseOutput;
import com.g5.restaurants.infrastructure.api.ReviewController;
import com.g5.restaurants.infrastructure.mappers.ReviewMapper;
import com.g5.review.api.ReviewApi;
import com.g5.review.model.CreateReviewDTO;
import com.g5.review.model.PaginateReviewDTO;
import com.g5.review.model.ReviewDTO;
import com.g5.review.model.UpdateReviewDTO;

import jakarta.servlet.http.HttpServletRequest;

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
    private ReviewListByRestaurantIdUseCase reviewListByRestaurantIdUseCase;

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

        ResponseEntity<ReviewDTO> response = reviewController.createReview(createDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(reviewDTO);
    }

    @Test
    void shouldUpdateReview() {
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

        ResponseEntity<ReviewDTO> response = reviewController.updateReview(id.toString(), updateDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(reviewDTO);
    }

    @Test
    void shouldFindReviewById() {
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

        ResponseEntity<ReviewDTO> response = reviewController.findReviewById(id.toString());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(reviewDTO);
    }

    @Test
    void shouldSearchReviewByRestaurantId() {
        var restaurantId = UUID.randomUUID().toString();

        var reviewOutput = new ReviewListByRestaurantIdUseCaseOutput(
                new BaseId(UUID.randomUUID().toString()),
                new BaseId(restaurantId),
                "Jane Doe",
                4,
                "Nice",
                LocalDateTime.of(2024, 12, 1, 14, 0),
                LocalDateTime.of(2024, 12, 1, 15, 0)
        );

        var reviewDTO = new ReviewDTO()
                .id(UUID.fromString(reviewOutput.id().value()))
                .restaurantId(UUID.fromString(reviewOutput.restaurantId().value()))
                .reviewerName("Jane Doe")
                .rating(4)
                .comments("Nice")
                .createdAt(OffsetDateTime.of(reviewOutput.createdAt(), ZoneOffset.UTC))
                .updatedAt(OffsetDateTime.of(reviewOutput.updatedAt(), ZoneOffset.UTC));

        when(reviewListByRestaurantIdUseCase.execute(any(ReviewListByRestaurantIdUseCaseInput.class)))
                .thenReturn(List.of(reviewOutput));
        when(reviewMapper.toDTOFromListByRestaurantOutput(reviewOutput)).thenReturn(reviewDTO);

        ResponseEntity<PaginateReviewDTO> response = reviewController.searchReviewByRestaurantId(restaurantId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).hasSize(1);
        assertThat(response.getBody().get(0)).isEqualTo(reviewDTO);
    }

    @Test
    void shouldFindReviews() {
        var reviewOutput = new ReviewListUseCaseOutput(
                new BaseId(UUID.randomUUID().toString()),
                new BaseId(UUID.randomUUID().toString()),
                "John Doe",
                5,
                "Good",
                LocalDateTime.of(2024, 12, 1, 14, 0),
                LocalDateTime.of(2024, 12, 1, 15, 0)
        );

        var reviewDTO = new ReviewDTO()
                .id(UUID.fromString(reviewOutput.id().value()))
                .restaurantId(UUID.fromString(reviewOutput.restaurantId().value()))
                .reviewerName("John Doe")
                .rating(5)
                .comments("Good")
                .createdAt(OffsetDateTime.of(reviewOutput.createdAt(), ZoneOffset.UTC))
                .updatedAt(OffsetDateTime.of(reviewOutput.updatedAt(), ZoneOffset.UTC));

        when(reviewListUseCase.execute()).thenReturn(List.of(reviewOutput));
        when(reviewMapper.toDTOFromListOutput(reviewOutput)).thenReturn(reviewDTO);

        ResponseEntity<PaginateReviewDTO> response = reviewController.findReviews();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).hasSize(1);
        assertThat(response.getBody().get(0)).isEqualTo(reviewDTO);
    }

    @Test
    void shouldDeleteReview() {
        var id = "some-review-id";

        ResponseEntity<Void> response = reviewController.deleteReview(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(reviewDeleteUseCase).execute(id);
    }

    @Test
    void shouldCoverDefaultMethodsWithNoObjectMapper() {
        ReviewApi api = new ReviewApi() {};

        ResponseEntity<ReviewDTO> createResponse = api.createReview(new CreateReviewDTO());
        ResponseEntity<ReviewDTO> findByIdResponse = api.findReviewById("id");
        ResponseEntity<PaginateReviewDTO> findReviewsResponse = api.findReviews();
        ResponseEntity<PaginateReviewDTO> searchByRestaurant = api.searchReviewByRestaurantId("restId");
        ResponseEntity<ReviewDTO> updateResponse = api.updateReview("id", new UpdateReviewDTO());
        ResponseEntity<Void> deleteResponse = api.deleteReview("id");

        assertThat(createResponse.getStatusCode().is5xxServerError() || createResponse.getStatusCode().is4xxClientError()).isTrue();
        assertThat(findByIdResponse.getStatusCode().is5xxServerError() || findByIdResponse.getStatusCode().is4xxClientError()).isTrue();
        assertThat(findReviewsResponse.getStatusCode().is5xxServerError() || findReviewsResponse.getStatusCode().is4xxClientError()).isTrue();
        assertThat(searchByRestaurant.getStatusCode().is5xxServerError() || searchByRestaurant.getStatusCode().is4xxClientError()).isTrue();
        assertThat(updateResponse.getStatusCode().is5xxServerError() || updateResponse.getStatusCode().is4xxClientError()).isTrue();
        assertThat(deleteResponse.getStatusCode().is5xxServerError() || deleteResponse.getStatusCode().is4xxClientError()).isTrue();
    }

    @Test
    void shouldCoverDefaultMethodsWithObjectMapperAndJsonAccept() throws IOException {
        ReviewApi api = new ReviewApi() {
            @Override
            public Optional<ObjectMapper> getObjectMapper() {
                ObjectMapper mapper = new ObjectMapper();
                mapper.findAndRegisterModules();
                return Optional.of(mapper);
            }

            @Override
            public Optional<HttpServletRequest> getRequest() {
                HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
                Mockito.when(request.getHeader("Accept")).thenReturn("application/json");
                return Optional.of(request);
            }
        };

        ResponseEntity<ReviewDTO> createResponse = api.createReview(new CreateReviewDTO());
        ResponseEntity<ReviewDTO> findByIdResponse = api.findReviewById("id");
        ResponseEntity<PaginateReviewDTO> findReviewsResponse = api.findReviews();
        ResponseEntity<PaginateReviewDTO> searchByRestaurant = api.searchReviewByRestaurantId("restId");
        ResponseEntity<ReviewDTO> updateResponse = api.updateReview("id", new UpdateReviewDTO());
        ResponseEntity<Void> deleteResponse = api.deleteReview("id");

        assertThat(createResponse.getStatusCode().value()).isEqualTo(501);
        assertThat(findByIdResponse.getStatusCode().value()).isEqualTo(501);
        assertThat(findReviewsResponse.getStatusCode().value()).isEqualTo(501);
        assertThat(searchByRestaurant.getStatusCode().value()).isEqualTo(501);
        assertThat(updateResponse.getStatusCode().value()).isEqualTo(501);
        assertThat(deleteResponse.getStatusCode().value()).isEqualTo(501);
    }

    @Test
    void shouldCoverScenarioWithDifferentAcceptHeader() throws IOException {
        ReviewApi api = new ReviewApi() {
            @Override
            public Optional<ObjectMapper> getObjectMapper() {
                ObjectMapper mapper = Mockito.mock(ObjectMapper.class);
                return Optional.of(mapper);
            }

            @Override
            public Optional<HttpServletRequest> getRequest() {
                HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
                Mockito.when(request.getHeader("Accept")).thenReturn("text/plain");
                return Optional.of(request);
            }
        };

        ResponseEntity<ReviewDTO> createResponse = api.createReview(new CreateReviewDTO());
        ResponseEntity<ReviewDTO> findByIdResponse = api.findReviewById("id");
        ResponseEntity<PaginateReviewDTO> findReviewsResponse = api.findReviews();
        ResponseEntity<PaginateReviewDTO> searchByRestaurant = api.searchReviewByRestaurantId("restId");
        ResponseEntity<ReviewDTO> updateResponse = api.updateReview("id", new UpdateReviewDTO());
        ResponseEntity<Void> deleteResponse = api.deleteReview("id");

        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_IMPLEMENTED);
        assertThat(findByIdResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_IMPLEMENTED);
        assertThat(findReviewsResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_IMPLEMENTED);
        assertThat(searchByRestaurant.getStatusCode()).isEqualTo(HttpStatus.NOT_IMPLEMENTED);
        assertThat(updateResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_IMPLEMENTED);
        assertThat(deleteResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_IMPLEMENTED);
    }


}
