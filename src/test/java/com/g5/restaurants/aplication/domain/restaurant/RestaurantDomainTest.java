package com.g5.restaurants.aplication.domain.restaurant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import com.g5.restaurant.model.RestaurantDTO;
import com.g5.restaurants.aplication.domain.base.BaseId;

class RestaurantDomainTest {

    @Test
    void shouldCreateNewRestaurant() {
        // Arrange
        var name = "Tia Nicole";
        var numberOfTables = 10;
        var address = "Rua das Flores, 123";
        var city = "São Paulo";
        var state = "SP";
        var type = RestaurantDTO.TypeEnum.BRAZILIAN;
        var openedAt = LocalTime.of(10, 0);
        var closedAt = LocalTime.of(22, 0);

        // Act
        var restaurant = Restaurant.newRestaurant(
            name,
            numberOfTables,
            address,
            city,
            state,
            type,
            openedAt,
            closedAt
        );

        // Assert
        assertThat(restaurant).isNotNull();
        assertThat(restaurant.getId()).isNotNull();
        assertThat(restaurant.getName()).isEqualTo(name);
        assertThat(restaurant.getNumberOfTables()).isEqualTo(numberOfTables);
        assertThat(restaurant.getAddress()).isEqualTo(address);
        assertThat(restaurant.getCity()).isEqualTo(city);
        assertThat(restaurant.getState()).isEqualTo(state);
        assertThat(restaurant.getType()).isEqualTo(type);
        assertThat(restaurant.getOpenedAt()).isEqualTo(openedAt);
        assertThat(restaurant.getClosedAt()).isEqualTo(closedAt);
    }

    @Test
    void shouldUpdateRestaurantDetails() {
        // Arrange
        var restaurant = new Restaurant(
            BaseId.generate(),
            "Tia Nicole",
            10,
            "Rua das Flores, 123",
            "São Paulo",
            "SP",
            RestaurantDTO.TypeEnum.BRAZILIAN,
            LocalTime.of(10, 0),
            LocalTime.of(22, 0)
        );

        var newName = "Cantina Italiana";
        var newNumberOfTables = 15;
        var newAddress = "Av. Paulista, 100";
        var newCity = "Rio de Janeiro";
        var newState = "RJ";
        var newType = RestaurantDTO.TypeEnum.ITALIAN;
        var newOpenedAt = LocalTime.of(11, 0);
        var newClosedAt = LocalTime.of(23, 0);

        // Act
        restaurant.update(
            newName,
            newNumberOfTables,
            newAddress,
            newCity,
            newState,
            newType,
            newOpenedAt,
            newClosedAt
        );

        // Assert
        assertThat(restaurant.getName()).isEqualTo(newName);
        assertThat(restaurant.getNumberOfTables()).isEqualTo(newNumberOfTables);
        assertThat(restaurant.getAddress()).isEqualTo(newAddress);
        assertThat(restaurant.getCity()).isEqualTo(newCity);
        assertThat(restaurant.getState()).isEqualTo(newState);
        assertThat(restaurant.getType()).isEqualTo(newType);
        assertThat(restaurant.getOpenedAt()).isEqualTo(newOpenedAt);
        assertThat(restaurant.getClosedAt()).isEqualTo(newClosedAt);
    }

    @Test
    void shouldThrowExceptionWhenNumberOfTablesIsInvalid() {
        // Arrange
        var name = "Tia Nicole";
        var invalidNumberOfTables = 0;
        var address = "Rua das Flores, 123";
        var city = "São Paulo";
        var state = "SP";
        var type = RestaurantDTO.TypeEnum.BRAZILIAN;
        var openedAt = LocalTime.of(10, 0);
        var closedAt = LocalTime.of(22, 0);

        // Act & Assert
        assertThatThrownBy(() -> Restaurant.newRestaurant(
            name,
            invalidNumberOfTables,
            address,
            city,
            state,
            type,
            openedAt,
            closedAt
        )).isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Number of tables must be at least 1");
    }

    @Test
    void shouldVerifyEqualityBasedOnId() {
        // Arrange
        var id = BaseId.generate();
        var restaurant1 = new Restaurant(
            id,
            "Tia Nicole",
            10,
            "Rua das Flores, 123",
            "São Paulo",
            "SP",
            RestaurantDTO.TypeEnum.BRAZILIAN,
            LocalTime.of(10, 0),
            LocalTime.of(22, 0)
        );

        var restaurant2 = new Restaurant(
            id,
            "Cantina Italiana",
            15,
            "Av. Paulista, 100",
            "Rio de Janeiro",
            "RJ",
            RestaurantDTO.TypeEnum.ITALIAN,
            LocalTime.of(11, 0),
            LocalTime.of(23, 0)
        );

        // Assert
        assertThat(restaurant1).isEqualTo(restaurant2);
        assertThat(restaurant1.hashCode()).isEqualTo(restaurant2.hashCode());
    }

    @Test
    void shouldVerifyToStringMethod() {
        // Arrange
        var restaurant = new Restaurant(
            BaseId.generate(),
            "Tia Nicole",
            10,
            "Rua das Flores, 123",
            "São Paulo",
            "SP",
            RestaurantDTO.TypeEnum.BRAZILIAN,
            LocalTime.of(10, 0),
            LocalTime.of(22, 0)
        );

        // Act
        var result = restaurant.toString();

        // Assert
        assertThat(result).contains("Tia Nicole");
        assertThat(result).contains("10");
        assertThat(result).contains("Rua das Flores, 123");
        assertThat(result).contains("São Paulo");
        assertThat(result).contains("SP");
        assertThat(result).contains("BRAZILIAN");
    }
}
