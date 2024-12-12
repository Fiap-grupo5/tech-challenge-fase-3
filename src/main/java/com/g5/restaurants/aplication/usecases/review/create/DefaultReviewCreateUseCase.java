package com.g5.restaurants.aplication.usecases.review.create;

import com.g5.restaurants.aplication.domain.base.BaseId;
import com.g5.restaurants.aplication.domain.review.Review;
import com.g5.restaurants.aplication.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultReviewCreateUseCase extends ReviewCreateUseCase {
    private final ReviewRepository reviewRepository;

    @Override
    public ReviewCreateUseCaseOutput execute(ReviewCreateUseCaseInput input) {
        final var newReview =
                Review.newReview(BaseId.from(input.restaurantId()), input.reviewerName(), input.rating(), input.comments());
        return ReviewCreateUseCaseOutput.from(reviewRepository.create(newReview));
    }
}
