package com.g5.restaurants.aplication.usecases.review.delete;

import com.g5.restaurants.aplication.domain.base.BaseId;
import com.g5.restaurants.aplication.exceptions.CommonException;
import com.g5.restaurants.aplication.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

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
