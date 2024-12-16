package com.g5.restaurants.infrastructure.config;

import com.g5.restaurants.aplication.repositories.ReservationRepository;
import com.g5.restaurants.aplication.repositories.RestaurantRepository;
import com.g5.restaurants.aplication.repositories.ReviewRepository;
import com.g5.restaurants.aplication.usecases.reservation.create.DefaultReservationCreateUseCase;
import com.g5.restaurants.aplication.usecases.reservation.create.ReservationCreateUseCase;
import com.g5.restaurants.aplication.usecases.reservation.delete.DefaultReservationDeleteUseCase;
import com.g5.restaurants.aplication.usecases.reservation.delete.ReservationDeleteUseCase;
import com.g5.restaurants.aplication.usecases.reservation.retrive.get.DefaultReservationGetByIdUseCase;
import com.g5.restaurants.aplication.usecases.reservation.retrive.get.ReservationGetByIdUseCase;
import com.g5.restaurants.aplication.usecases.reservation.retrive.list.DefaultReservationListUseCase;
import com.g5.restaurants.aplication.usecases.reservation.retrive.list.ReservationListUseCase;
import com.g5.restaurants.aplication.usecases.reservation.update.DefaultReservationUpdateUseCase;
import com.g5.restaurants.aplication.usecases.reservation.update.ReservationUpdateUseCase;
import com.g5.restaurants.aplication.usecases.restaurant.create.DefaultRestaurantCreateUseCase;
import com.g5.restaurants.aplication.usecases.restaurant.create.RestaurantCreateUseCase;
import com.g5.restaurants.aplication.usecases.restaurant.delete.DefaultRestaurantDeleteUseCase;
import com.g5.restaurants.aplication.usecases.restaurant.delete.RestaurantDeleteUseCase;
import com.g5.restaurants.aplication.usecases.restaurant.retrive.get.DefaultRestaurantGetByIdUseCase;
import com.g5.restaurants.aplication.usecases.restaurant.retrive.get.RestaurantGetByIdUseCase;
import com.g5.restaurants.aplication.usecases.restaurant.retrive.list.DefaultRestaurantListUseCase;
import com.g5.restaurants.aplication.usecases.restaurant.retrive.list.RestaurantListUseCase;
import com.g5.restaurants.aplication.usecases.restaurant.update.DefaultRestaurantUpdateUseCase;
import com.g5.restaurants.aplication.usecases.restaurant.update.RestaurantUpdateUseCase;
import com.g5.restaurants.aplication.usecases.review.create.DefaultReviewCreateUseCase;
import com.g5.restaurants.aplication.usecases.review.create.ReviewCreateUseCase;
import com.g5.restaurants.aplication.usecases.review.delete.DefaultReviewDeleteUseCase;
import com.g5.restaurants.aplication.usecases.review.delete.ReviewDeleteUseCase;
import com.g5.restaurants.aplication.usecases.review.retrieve.get.DefaultReviewGetByIdUseCase;
import com.g5.restaurants.aplication.usecases.review.retrieve.get.ReviewGetByIdUseCase;
import com.g5.restaurants.aplication.usecases.review.retrieve.list.DefaultReviewListUseCase;
import com.g5.restaurants.aplication.usecases.review.retrieve.list.ReviewListUseCase;
import com.g5.restaurants.aplication.usecases.review.retrieve.list.byRestaurantId.DefaultReviewListByRestaurantIdUseCase;
import com.g5.restaurants.aplication.usecases.review.retrieve.list.byRestaurantId.ReviewListByRestaurantIdUseCase;
import com.g5.restaurants.aplication.usecases.review.update.DefaultReviewUpdateUseCase;
import com.g5.restaurants.aplication.usecases.review.update.ReviewUpdateUseCase;
import com.g5.restaurants.infrastructure.persistence.repositories.ReservationMongoRepository;
import com.g5.restaurants.infrastructure.persistence.repositories.RestaurantMongoRepository;
import com.g5.restaurants.infrastructure.persistence.repositories.ReviewMongoRepository;
import com.g5.restaurants.infrastructure.repositories.ReservationRepositoryImpl;
import com.g5.restaurants.infrastructure.repositories.RestaurantRepositoryImpl;
import com.g5.restaurants.infrastructure.repositories.ReviewRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public ReviewRepository reviewRepository(
            final ReviewMongoRepository reviewMongoRepository) {
        return new ReviewRepositoryImpl(reviewMongoRepository);
    }

    @Bean
    public ReviewCreateUseCase reviewCreateUseCase(
            final ReviewRepository reviewRepository) {
        return new DefaultReviewCreateUseCase(reviewRepository);
    }

    @Bean
    public ReviewListUseCase reviewListUseCase(final ReviewRepository reviewRepository) {
        return new DefaultReviewListUseCase(reviewRepository);
    }

    @Bean
    public ReviewGetByIdUseCase reviewGetByIdUseCase(
            final ReviewRepository reviewRepository) {
        return new DefaultReviewGetByIdUseCase(reviewRepository);
    }

    @Bean
    public ReviewListByRestaurantIdUseCase reviewListByRestaurantIdUseCase(
            final ReviewRepository reviewRepository) {
        return new DefaultReviewListByRestaurantIdUseCase(reviewRepository);
    }

    @Bean
    public ReviewUpdateUseCase reviewUpdateUseCase(
            final ReviewRepository reviewRepository) {
        return new DefaultReviewUpdateUseCase(reviewRepository);
    }

    @Bean
    public ReviewDeleteUseCase reviewDeleteUseCase(
            final ReviewRepository reviewRepository) {
        return new DefaultReviewDeleteUseCase(reviewRepository);
    }

    @Bean
    public ReservationRepository reservationRepository(final ReservationMongoRepository reservationMongoRepository) {
        return new ReservationRepositoryImpl(reservationMongoRepository);
    }

    @Bean
    public ReservationCreateUseCase reservationCreateUseCase(final ReservationRepository reservationRepository) {
        return new DefaultReservationCreateUseCase(reservationRepository);
    }

    @Bean
    public ReservationDeleteUseCase reservationDeleteUseCase(final ReservationRepository reservationRepository) {
        return new DefaultReservationDeleteUseCase(reservationRepository);
    }

    @Bean
    public ReservationListUseCase reservationListUseCase(final ReservationRepository reservationRepository) {
        return new DefaultReservationListUseCase(reservationRepository);
    }

    @Bean
    public ReservationGetByIdUseCase reservationGetByIdUseCase(final ReservationRepository reservationRepository) {
        return new DefaultReservationGetByIdUseCase(reservationRepository);
    }

    @Bean
    public ReservationUpdateUseCase reservationUpdateUseCase(final ReservationRepository reservationRepository) {
        return new DefaultReservationUpdateUseCase(reservationRepository);
    }

    @Bean
    public RestaurantRepository restaurantRepository(
            final RestaurantMongoRepository restaurantMongoRepository) {
        return new RestaurantRepositoryImpl(restaurantMongoRepository);
    }

    @Bean
    public RestaurantCreateUseCase restaurantCreateUseCase(
            final RestaurantRepository restaurantRepository) {
        return new DefaultRestaurantCreateUseCase(restaurantRepository);
    }

    @Bean
    public RestaurantListUseCase restaurantListUseCase(
            final RestaurantRepository restaurantRepository) {
        return new DefaultRestaurantListUseCase(restaurantRepository);
    }

    @Bean
    public RestaurantGetByIdUseCase restaurantGetByIdUseCase(
            final RestaurantRepository restaurantRepository) {
        return new DefaultRestaurantGetByIdUseCase(restaurantRepository);
    }

    @Bean
    public RestaurantUpdateUseCase RestaurantUpdateUseCase(
            final RestaurantRepository restaurantRepository) {
        return new DefaultRestaurantUpdateUseCase(restaurantRepository);
    }

    @Bean
    public RestaurantDeleteUseCase RestaurantDeleteUseCase(
            final RestaurantRepository restaurantRepository) {
        return new DefaultRestaurantDeleteUseCase(restaurantRepository);
    }
}
