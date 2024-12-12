package com.g5.restaurants.aplication.usecases.review.retrive.list.byRestaurantId;

import com.g5.restaurants.aplication.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DefaultReviewListByRestaurantIdUseCase extends ReviewListByRestaurantIdUseCase {
    private final ReviewRepository reviewRepository;

    @Override
    public List<ReviewListByRestaurantIdUseCaseOutput> execute(ReviewListByRestaurantIdUseCaseInput input) {
            return reviewRepository
                    .findByRestaurantId(input.restaurantId())
                    .stream()
                    .map(ReviewListByRestaurantIdUseCaseOutput::from)
                    .collect(Collectors.toList());
    }
}
