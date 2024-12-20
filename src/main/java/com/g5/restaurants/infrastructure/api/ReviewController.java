package com.g5.restaurants.infrastructure.api;

import com.g5.restaurants.aplication.usecases.review.create.ReviewCreateUseCase;
import com.g5.restaurants.aplication.usecases.review.delete.ReviewDeleteUseCase;
import com.g5.restaurants.aplication.usecases.review.retrieve.get.ReviewGetByIdUseCase;
import com.g5.restaurants.aplication.usecases.review.retrieve.list.ReviewListUseCase;
import com.g5.restaurants.aplication.usecases.review.retrieve.list.byRestaurantId.ReviewListByRestaurantIdUseCase;
import com.g5.restaurants.aplication.usecases.review.update.ReviewUpdateUseCase;
import com.g5.restaurants.infrastructure.mappers.ReviewMapper;
import com.g5.review.api.ReviewApi;
import com.g5.review.model.CreateReviewDTO;
import com.g5.review.model.PaginateReviewDTO;
import com.g5.review.model.ReviewDTO;
import com.g5.review.model.UpdateReviewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ReviewController implements ReviewApi {
    private static final ReviewMapper reviewMapper = ReviewMapper.INSTANCE;
    private final ReviewCreateUseCase reviewCreateUseCase;
    private final ReviewListUseCase reviewListUseCase;
    private final ReviewGetByIdUseCase reviewGetByIdUseCase;
    private final ReviewListByRestaurantIdUseCase reviewListByRestaurantIdUseCase;
    private final ReviewUpdateUseCase reviewUpdateUseCase;
    private final ReviewDeleteUseCase reviewDeleteUseCase;

    @Override
    public ResponseEntity<ReviewDTO> updateReview(
            final String id, final UpdateReviewDTO body) {
        final var input = reviewMapper.fromDTO(id, body);
        final var output = reviewMapper.toDTOFromUpdateOutput(reviewUpdateUseCase.execute(input));
        return ResponseEntity.ok(output);
    }

    @Override
    public ResponseEntity<ReviewDTO> findReviewById(final String id) {
        final var output = reviewMapper.toDTOFromGetByIdOutput(reviewGetByIdUseCase.execute(id));
        return ResponseEntity.ok(output);
    }

    @Override
    public ResponseEntity<PaginateReviewDTO> searchReviewByRestaurantId(
            @PathVariable("restaurantId") final String restaurantId) { // Corrige o nome do parâmetro
        final var input = reviewMapper.fromDTO(restaurantId);
        final var reviews =
                reviewListByRestaurantIdUseCase.execute(input)
                        .stream()
                        .map(reviewMapper::toDTOFromListByRestaurantOutput)
                        .collect(Collectors.toList());
    
        final var paginatedReviews = new PaginateReviewDTO();
        paginatedReviews.addAll(reviews);
    
        return ResponseEntity.ok(paginatedReviews);
    }
    

    @Override
    public ResponseEntity<PaginateReviewDTO> findReviews() {
        final var review = reviewListUseCase.execute()
                .stream()
                .map(reviewMapper::toDTOFromListOutput)
                .collect(Collectors.toList());
        final var paginatedReviews = new PaginateReviewDTO();
        paginatedReviews.addAll(review);
        return ResponseEntity.ok(paginatedReviews);
    }

    @Override
    public ResponseEntity<ReviewDTO> createReview(final CreateReviewDTO body) {
        final var useCaseInput = reviewMapper.fromDTO(body);
        final var useCaseOutput = reviewCreateUseCase.execute(useCaseInput);
        var uri = URI.create("/avaliacoes/" + useCaseOutput.id());
        return ResponseEntity.created(uri).body(reviewMapper.toDTOFromCreateOutput(useCaseOutput));
    }

    @Override
    public ResponseEntity<Void> deleteReview(String id) {
        reviewDeleteUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
