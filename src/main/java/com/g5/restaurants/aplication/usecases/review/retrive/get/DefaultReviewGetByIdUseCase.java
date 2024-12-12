package com.g5.restaurants.aplication.usecases.review.retrive.get;

import com.g5.restaurants.aplication.domain.base.BaseId;
import com.g5.restaurants.aplication.exceptions.CommonException;
import com.g5.restaurants.aplication.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class DefaultReviewGetByIdUseCase extends ReviewGetByIdUseCase{
    private final ReviewRepository reviewRepository;

    @Override
    public ReviewGetByIdUseCaseOutput execute(String input) {
        return reviewRepository
                .findById(input)
                .map(ReviewGetByIdUseCaseOutput::from)
                .orElseThrow(
                        () -> new CommonException("Review with ID %s not found.".formatted(input), HttpStatus.NOT_FOUND));
    }
}
