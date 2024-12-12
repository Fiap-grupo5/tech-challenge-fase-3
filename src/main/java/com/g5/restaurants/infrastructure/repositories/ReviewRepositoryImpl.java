package com.g5.restaurants.infrastructure.repositories;

import com.g5.restaurants.aplication.domain.review.Review;
import com.g5.restaurants.aplication.repositories.ReviewRepository;
import com.g5.restaurants.infrastructure.persistence.entities.ReviewEntity;
import com.g5.restaurants.infrastructure.persistence.repositories.ReviewMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepository {
    private final ReviewMongoRepository reviewMongoRepository;

    @Override
    public Review create(Review review) {
        return save(review);
    }

    @Override
    public List<Review> findAll() {
        final var pageResult = reviewMongoRepository.findAll();

        return pageResult.stream().map(ReviewEntity::toReview).toList();
    }

    @Override
    public Optional<Review> findById(String id) {
        return reviewMongoRepository.findById(id).map(ReviewEntity::toReview);
    }

    @Override
    public List<Review> findByRestaurantId(String id) {
        final var pageResult = reviewMongoRepository.findByRestaurantId(id);
        return pageResult.stream().map(ReviewEntity::toReview).toList();
    }

    @Override
    public Review update(Review review) {
        return save(review);
    }

    @Override
    public void delete(String id) {
        reviewMongoRepository.deleteById(id);
    }

    private Review save(final Review review) {
        return reviewMongoRepository.save(ReviewEntity.of(review)).toReview();
    }
}
