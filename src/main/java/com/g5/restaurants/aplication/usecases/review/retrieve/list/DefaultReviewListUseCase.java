package com.g5.restaurants.aplication.usecases.review.retrieve.list;

import com.g5.restaurants.aplication.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DefaultReviewListUseCase extends ReviewListUseCase {
    private final ReviewRepository reviewRepository;

    @Override
    public List<ReviewListUseCaseOutput> execute() {
        return reviewRepository
                .findAll()
                .stream()
                .map(ReviewListUseCaseOutput::from)
                .collect(Collectors.toList());
    }
}
