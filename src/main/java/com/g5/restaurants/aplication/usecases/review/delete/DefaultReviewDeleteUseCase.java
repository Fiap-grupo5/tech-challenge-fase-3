package com.g5.restaurants.aplication.usecases.review.delete;

import org.springframework.http.HttpStatus;

import com.g5.restaurants.aplication.exceptions.CommonException;
import com.g5.restaurants.aplication.repositories.ReviewRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultReviewDeleteUseCase extends ReviewDeleteUseCase {
    private final ReviewRepository reviewRepository;

    @Override
    public void execute(String id) {
        reviewRepository.findById(id).orElseThrow(() -> new CommonException(
                "Review with ID %s not found!".formatted(id),
                HttpStatus.NOT_FOUND
        ));
        reviewRepository.delete(id);
    }
}
