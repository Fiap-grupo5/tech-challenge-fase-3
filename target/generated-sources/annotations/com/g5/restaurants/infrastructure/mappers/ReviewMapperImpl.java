package com.g5.restaurants.infrastructure.mappers;

import com.g5.restaurants.aplication.domain.base.BaseId;
import com.g5.restaurants.aplication.domain.review.Review;
import com.g5.restaurants.aplication.usecases.review.create.ReviewCreateUseCaseInput;
import com.g5.restaurants.aplication.usecases.review.create.ReviewCreateUseCaseOutput;
import com.g5.restaurants.aplication.usecases.review.retrive.get.ReviewGetByIdUseCaseOutput;
import com.g5.restaurants.aplication.usecases.review.retrive.list.ReviewListUseCaseOutput;
import com.g5.restaurants.aplication.usecases.review.retrive.list.byRestaurantId.ReviewListByRestaurantIdUseCaseInput;
import com.g5.restaurants.aplication.usecases.review.retrive.list.byRestaurantId.ReviewListByRestaurantIdUseCaseOutput;
import com.g5.restaurants.aplication.usecases.review.update.ReviewUpdateUseCaseInput;
import com.g5.restaurants.aplication.usecases.review.update.ReviewUpdateUseCaseOutput;
import com.g5.restaurants.infrastructure.persistence.entities.ReviewEntity;
import com.g5.review.model.CreateReviewDTO;
import com.g5.review.model.ReviewDTO;
import com.g5.review.model.UpdateReviewDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-12T04:50:24-0300",
    comments = "version: 1.6.0, compiler: javac, environment: Java 17.0.13 (Ubuntu)"
)
@Component
public class ReviewMapperImpl implements ReviewMapper {

    @Override
    public ReviewDTO toDTO(ReviewCreateUseCaseOutput output) {
        if ( output == null ) {
            return null;
        }

        ReviewDTO reviewDTO = new ReviewDTO();

        reviewDTO.setReviewerName( output.reviewerName() );
        reviewDTO.setRating( output.rating() );
        reviewDTO.setComments( output.comments() );

        reviewDTO.setId( java.util.UUID.fromString(output.id().toString()) );
        reviewDTO.setRestaurantId( java.util.UUID.fromString(output.restaurantId().toString()) );
        reviewDTO.setCreatedAt( mapOffsetDateTime(output.createdAt()) );
        reviewDTO.setUpdatedAt( mapOffsetDateTime(output.updatedAt()) );

        return reviewDTO;
    }

    @Override
    public ReviewDTO toDTO(ReviewGetByIdUseCaseOutput output) {
        if ( output == null ) {
            return null;
        }

        ReviewDTO reviewDTO = new ReviewDTO();

        reviewDTO.setReviewerName( output.reviewerName() );
        reviewDTO.setRating( output.rating() );
        reviewDTO.setComments( output.comments() );

        reviewDTO.setId( java.util.UUID.fromString(output.id().toString()) );
        reviewDTO.setRestaurantId( java.util.UUID.fromString(output.restaurantId().toString()) );
        reviewDTO.setCreatedAt( mapOffsetDateTime(output.createdAt()) );
        reviewDTO.setUpdatedAt( mapOffsetDateTime(output.updatedAt()) );

        return reviewDTO;
    }

    @Override
    public ReviewDTO toDTO(ReviewListUseCaseOutput output) {
        if ( output == null ) {
            return null;
        }

        ReviewDTO reviewDTO = new ReviewDTO();

        reviewDTO.setReviewerName( output.reviewerName() );
        reviewDTO.setRating( output.rating() );
        reviewDTO.setComments( output.comments() );

        reviewDTO.setId( java.util.UUID.fromString(output.id().toString()) );
        reviewDTO.setRestaurantId( java.util.UUID.fromString(output.restaurantId().toString()) );
        reviewDTO.setCreatedAt( mapOffsetDateTime(output.createdAt()) );
        reviewDTO.setUpdatedAt( mapOffsetDateTime(output.updatedAt()) );

        return reviewDTO;
    }

    @Override
    public ReviewDTO toDTO(ReviewListByRestaurantIdUseCaseOutput output) {
        if ( output == null ) {
            return null;
        }

        ReviewDTO reviewDTO = new ReviewDTO();

        reviewDTO.setReviewerName( output.reviewerName() );
        reviewDTO.setRating( output.rating() );
        reviewDTO.setComments( output.comments() );

        reviewDTO.setId( java.util.UUID.fromString(output.id().toString()) );
        reviewDTO.setRestaurantId( java.util.UUID.fromString(output.restaurantId().toString()) );
        reviewDTO.setCreatedAt( mapOffsetDateTime(output.createdAt()) );
        reviewDTO.setUpdatedAt( mapOffsetDateTime(output.updatedAt()) );

        return reviewDTO;
    }

    @Override
    public ReviewDTO toDTO(ReviewUpdateUseCaseOutput output) {
        if ( output == null ) {
            return null;
        }

        ReviewDTO reviewDTO = new ReviewDTO();

        reviewDTO.setReviewerName( output.reviewerName() );
        reviewDTO.setRating( output.rating() );
        reviewDTO.setComments( output.comments() );

        reviewDTO.setId( java.util.UUID.fromString(output.id().toString()) );
        reviewDTO.setRestaurantId( java.util.UUID.fromString(output.restaurantId().toString()) );
        reviewDTO.setCreatedAt( mapOffsetDateTime(output.createdAt()) );
        reviewDTO.setUpdatedAt( mapOffsetDateTime(output.updatedAt()) );

        return reviewDTO;
    }

    @Override
    public ReviewCreateUseCaseInput fromDTO(CreateReviewDTO dto) {
        if ( dto == null ) {
            return null;
        }

        String restaurantId = null;
        String reviewerName = null;
        Integer rating = null;
        String comments = null;

        if ( dto.getRestaurantId() != null ) {
            restaurantId = dto.getRestaurantId().toString();
        }
        reviewerName = dto.getReviewerName();
        rating = dto.getRating();
        comments = dto.getComments();

        ReviewCreateUseCaseInput reviewCreateUseCaseInput = new ReviewCreateUseCaseInput( restaurantId, reviewerName, rating, comments );

        return reviewCreateUseCaseInput;
    }

    @Override
    public ReviewListByRestaurantIdUseCaseInput fromDTO(String restaurantId) {
        if ( restaurantId == null ) {
            return null;
        }

        String restaurantId1 = null;

        restaurantId1 = restaurantId;

        ReviewListByRestaurantIdUseCaseInput reviewListByRestaurantIdUseCaseInput = new ReviewListByRestaurantIdUseCaseInput( restaurantId1 );

        return reviewListByRestaurantIdUseCaseInput;
    }

    @Override
    public ReviewUpdateUseCaseInput fromDTO(String id, UpdateReviewDTO dto) {
        if ( id == null && dto == null ) {
            return null;
        }

        String reviewerName = null;
        Integer rating = null;
        String comments = null;
        if ( dto != null ) {
            reviewerName = dto.getReviewerName();
            rating = dto.getRating();
            comments = dto.getComments();
        }
        String id1 = null;
        id1 = id;

        ReviewUpdateUseCaseInput reviewUpdateUseCaseInput = new ReviewUpdateUseCaseInput( id1, reviewerName, rating, comments );

        return reviewUpdateUseCaseInput;
    }

    @Override
    public Review toObject(ReviewEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Review review = new Review();

        review.setReviewerName( entity.getReviewerName() );
        review.setRating( entity.getRating() );
        review.setComments( entity.getComments() );
        review.setCreatedAt( entity.getCreatedAt() );
        review.setUpdatedAt( entity.getUpdatedAt() );

        review.setId( new BaseId(entity.getId()) );
        review.setRestaurantId( new BaseId(entity.getRestaurantId()) );

        return review;
    }
}
