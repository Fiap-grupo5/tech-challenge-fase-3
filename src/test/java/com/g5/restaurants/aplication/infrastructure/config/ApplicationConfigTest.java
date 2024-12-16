package com.g5.restaurants.aplication.infrastructure.config;

import com.g5.restaurants.aplication.repositories.*;
import com.g5.restaurants.aplication.usecases.reservation.create.ReservationCreateUseCase;
import com.g5.restaurants.aplication.usecases.reservation.delete.ReservationDeleteUseCase;
import com.g5.restaurants.aplication.usecases.reservation.retrive.get.ReservationGetByIdUseCase;
import com.g5.restaurants.aplication.usecases.reservation.retrive.list.ReservationListUseCase;
import com.g5.restaurants.aplication.usecases.reservation.update.ReservationUpdateUseCase;
import com.g5.restaurants.aplication.usecases.restaurant.create.RestaurantCreateUseCase;
import com.g5.restaurants.aplication.usecases.restaurant.delete.RestaurantDeleteUseCase;
import com.g5.restaurants.aplication.usecases.restaurant.retrive.get.RestaurantGetByIdUseCase;
import com.g5.restaurants.aplication.usecases.restaurant.retrive.list.RestaurantListUseCase;
import com.g5.restaurants.aplication.usecases.restaurant.update.RestaurantUpdateUseCase;
import com.g5.restaurants.aplication.usecases.review.create.ReviewCreateUseCase;
import com.g5.restaurants.aplication.usecases.review.delete.ReviewDeleteUseCase;
import com.g5.restaurants.aplication.usecases.review.retrieve.get.ReviewGetByIdUseCase;
import com.g5.restaurants.aplication.usecases.review.retrieve.list.ReviewListUseCase;
import com.g5.restaurants.aplication.usecases.review.retrieve.list.byRestaurantId.ReviewListByRestaurantIdUseCase;
import com.g5.restaurants.aplication.usecases.review.update.ReviewUpdateUseCase;
import com.g5.restaurants.infrastructure.config.ApplicationConfig;
import com.g5.restaurants.infrastructure.persistence.repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ApplicationConfigTest {

    private ApplicationConfig applicationConfig;

    @BeforeEach
    void setUp() {
        applicationConfig = new ApplicationConfig();
    }

    @Test
    void testReviewBeans() {
        ReviewMongoRepository reviewMongoRepository = mock(ReviewMongoRepository.class);
        ReviewRepository reviewRepository = applicationConfig.reviewRepository(reviewMongoRepository);
        assertNotNull(reviewRepository);

        ReviewCreateUseCase createUseCase = applicationConfig.reviewCreateUseCase(reviewRepository);
        ReviewListUseCase listUseCase = applicationConfig.reviewListUseCase(reviewRepository);
        ReviewGetByIdUseCase getByIdUseCase = applicationConfig.reviewGetByIdUseCase(reviewRepository);
        ReviewListByRestaurantIdUseCase listByRestaurantIdUseCase = applicationConfig.reviewListByRestaurantIdUseCase(reviewRepository);
        ReviewUpdateUseCase updateUseCase = applicationConfig.reviewUpdateUseCase(reviewRepository);
        ReviewDeleteUseCase deleteUseCase = applicationConfig.reviewDeleteUseCase(reviewRepository);

        assertNotNull(createUseCase);
        assertNotNull(listUseCase);
        assertNotNull(getByIdUseCase);
        assertNotNull(listByRestaurantIdUseCase);
        assertNotNull(updateUseCase);
        assertNotNull(deleteUseCase);
    }

    @Test
    void testReservationBeans() {
        ReservationMongoRepository reservationMongoRepository = mock(ReservationMongoRepository.class);
        ReservationRepository reservationRepository = applicationConfig.reservationRepository(reservationMongoRepository);
        assertNotNull(reservationRepository);

        ReservationCreateUseCase createUseCase = applicationConfig.reservationCreateUseCase(reservationRepository);
        ReservationDeleteUseCase deleteUseCase = applicationConfig.reservationDeleteUseCase(reservationRepository);
        ReservationListUseCase listUseCase = applicationConfig.reservationListUseCase(reservationRepository);
        ReservationGetByIdUseCase getByIdUseCase = applicationConfig.reservationGetByIdUseCase(reservationRepository);
        ReservationUpdateUseCase updateUseCase = applicationConfig.reservationUpdateUseCase(reservationRepository);

        assertNotNull(createUseCase);
        assertNotNull(deleteUseCase);
        assertNotNull(listUseCase);
        assertNotNull(getByIdUseCase);
        assertNotNull(updateUseCase);
    }

    @Test
    void testRestaurantBeans() {
        RestaurantMongoRepository restaurantMongoRepository = mock(RestaurantMongoRepository.class);
        RestaurantRepository restaurantRepository = applicationConfig.restaurantRepository(restaurantMongoRepository);
        assertNotNull(restaurantRepository);

        RestaurantCreateUseCase createUseCase = applicationConfig.restaurantCreateUseCase(restaurantRepository);
        RestaurantListUseCase listUseCase = applicationConfig.restaurantListUseCase(restaurantRepository);
        RestaurantGetByIdUseCase getByIdUseCase = applicationConfig.restaurantGetByIdUseCase(restaurantRepository);
        RestaurantUpdateUseCase updateUseCase = applicationConfig.RestaurantUpdateUseCase(restaurantRepository);
        RestaurantDeleteUseCase deleteUseCase = applicationConfig.RestaurantDeleteUseCase(restaurantRepository);

        assertNotNull(createUseCase);
        assertNotNull(listUseCase);
        assertNotNull(getByIdUseCase);
        assertNotNull(updateUseCase);
        assertNotNull(deleteUseCase);
    }
}
