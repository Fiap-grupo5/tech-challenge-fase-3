package com.g5.restaurants.aplication.infrastructure.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import com.g5.restaurant.model.RestaurantDTO;
import com.g5.restaurants.aplication.domain.restaurant.Restaurant;
import com.g5.restaurants.infrastructure.persistence.repositories.RestaurantMongoRepository;
import com.g5.restaurants.infrastructure.repositories.RestaurantRepositoryImpl;

@DataMongoTest
class RestaurantRepositoryImplTest {

    @Autowired
    private RestaurantMongoRepository restaurantMongoRepository;

    private RestaurantRepositoryImpl restaurantRepository;

    @BeforeEach
    void setUp() {
        restaurantRepository = new RestaurantRepositoryImpl(restaurantMongoRepository);
        restaurantMongoRepository.deleteAll(); // Limpa os dados antes de cada teste
    }

    @Test
    void shouldCreateRestaurant() {
        // Arrange
        Restaurant restaurant = createSampleRestaurant();

        // Act
        Restaurant savedRestaurant = restaurantRepository.create(restaurant);

        // Assert
        assertThat(savedRestaurant).isNotNull();
        assertThat(savedRestaurant.getId()).isEqualTo(restaurant.getId());
        assertThat(savedRestaurant.getName()).isEqualTo("Cantina Italiana");
    }

    @Test
    void shouldFindRestaurantById() {
        // Arrange
        Restaurant restaurant = createSampleRestaurant();
        restaurantRepository.create(restaurant);

        // Act
        Optional<Restaurant> foundRestaurant = restaurantRepository.findById(restaurant.getId().toString());

        // Assert
        assertThat(foundRestaurant).isPresent();
        assertThat(foundRestaurant.get().getName()).isEqualTo("Cantina Italiana");
    }

    @Test
    void shouldFindAllRestaurants() {
        // Arrange
        Restaurant restaurant1 = createSampleRestaurant();
        Restaurant restaurant2 = createSampleRestaurant("Tia Nicole", 20);
        restaurantRepository.create(restaurant1);
        restaurantRepository.create(restaurant2);

        // Act
        var restaurants = restaurantRepository.findAll();

        // Assert
        assertThat(restaurants).hasSize(2);
        assertThat(restaurants).extracting("name").contains("Cantina Italiana", "Tia Nicole");
    }

    @Test
    void shouldUpdateRestaurant() {
        // Arrange
        Restaurant restaurant = createSampleRestaurant();
        restaurantRepository.create(restaurant);

        restaurant.update(
                "Cantina Renovada",
                25,
                "Rua Nova, 456",
                "Rio de Janeiro",
                "RJ",
                RestaurantDTO.TypeEnum.ITALIAN,
                LocalTime.of(12, 0),
                LocalTime.of(22, 0)
        );

        // Act
        Restaurant updatedRestaurant = restaurantRepository.update(restaurant);

        // Assert
        assertThat(updatedRestaurant.getName()).isEqualTo("Cantina Renovada");
        assertThat(updatedRestaurant.getNumberOfTables()).isEqualTo(25);
        assertThat(updatedRestaurant.getAddress()).isEqualTo("Rua Nova, 456");
        assertThat(updatedRestaurant.getCity()).isEqualTo("Rio de Janeiro");
        assertThat(updatedRestaurant.getState()).isEqualTo("RJ");
        assertThat(updatedRestaurant.getOpenedAt()).isEqualTo(LocalTime.of(12, 0));
        assertThat(updatedRestaurant.getClosedAt()).isEqualTo(LocalTime.of(22, 0));
    }

    @Test
    void shouldDeleteRestaurantById() {
        // Arrange
        Restaurant restaurant = createSampleRestaurant();
        restaurantRepository.create(restaurant);

        // Act
        restaurantRepository.delete(restaurant.getId().toString());

        // Assert
        Optional<Restaurant> deletedRestaurant = restaurantRepository.findById(restaurant.getId().toString());
        assertThat(deletedRestaurant).isEmpty();
    }

    private Restaurant createSampleRestaurant() {
        return createSampleRestaurant("Cantina Italiana", 15);
    }

    private Restaurant createSampleRestaurant(String name, int numberOfTables) {
        return Restaurant.newRestaurant(
                name,
                numberOfTables,
                "Av. Paulista, 100",
                "São Paulo",
                "SP",
                RestaurantDTO.TypeEnum.ITALIAN,
                LocalTime.of(11, 0),
                LocalTime.of(23, 0)
        );
    }
}
