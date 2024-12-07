package com.g5.restaurants.aplication.usecases.review.update;

import com.g5.restaurants.aplication.domain.base.BaseId;
import com.g5.restaurants.aplication.exceptions.CommonException;
import com.g5.restaurants.aplication.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class DefaultReviewUpdateUseCase extends ReviewUpdateUseCase{
    private final ReviewRepository reviewRepository;

    @Override
    public ReviewUpdateUseCaseOutput execute(ReviewUpdateUseCaseInput input) {
        final var id = new BaseId(input.id());
        final var review = reviewRepository
                                .findById(input.id())
                                .orElseThrow(
                                    () ->
                                        new CommonException(
                                                "Review with ID %s not found!.".formatted(id.toString()),
                                                HttpStatus.NOT_FOUND));
        review.update(input.reviewerName(), input.rating(), input.comments());
        return ReviewUpdateUseCaseOutput.from(reviewRepository.update(review));
    }
}
