package com.g5.restaurants.aplication.usecases.review.retrieve.get;

import org.springframework.http.HttpStatus;

import com.g5.restaurants.aplication.exceptions.CommonException;
import com.g5.restaurants.aplication.repositories.ReviewRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultReviewGetByIdUseCase extends ReviewGetByIdUseCase{
    private final ReviewRepository reviewRepository;

    @Override
    public ReviewGetByIdUseCaseOutput execute(String input) {
        return reviewRepository
                .findById(input)
                .map(ReviewGetByIdUseCaseOutput::from)
                .orElseThrow(
                        () -> new CommonException("Review with ID %s not found!.".formatted(input), HttpStatus.NOT_FOUND));
    }
}
