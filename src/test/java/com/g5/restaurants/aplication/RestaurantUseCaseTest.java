package com.g5.restaurants.aplication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.g5.restaurant.model.RestaurantDTO;
import com.g5.restaurants.aplication.domain.base.BaseId;
import com.g5.restaurants.aplication.domain.restaurant.Restaurant;
import com.g5.restaurants.aplication.exceptions.CommonException;
import com.g5.restaurants.aplication.repositories.RestaurantRepository;
import com.g5.restaurants.aplication.usecases.restaurant.create.DefaultRestaurantCreateUseCase;
import com.g5.restaurants.aplication.usecases.restaurant.create.RestaurantCreateUseCaseInput;
import com.g5.restaurants.aplication.usecases.restaurant.delete.DefaultRestaurantDeleteUseCase;
import com.g5.restaurants.aplication.usecases.restaurant.retrive.get.DefaultRestaurantGetByIdUseCase;
import com.g5.restaurants.aplication.usecases.restaurant.retrive.list.DefaultRestaurantListUseCase;
import com.g5.restaurants.aplication.usecases.restaurant.update.DefaultRestaurantUpdateUseCase;
import com.g5.restaurants.aplication.usecases.restaurant.update.RestaurantUpdateUseCaseInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class RestaurantUseCaseTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private DefaultRestaurantCreateUseCase createUseCase;

    @InjectMocks
    private DefaultRestaurantDeleteUseCase deleteUseCase;

    @InjectMocks
    private DefaultRestaurantGetByIdUseCase getByIdUseCase;

    @InjectMocks
    private DefaultRestaurantListUseCase listUseCase;

    @InjectMocks
    private DefaultRestaurantUpdateUseCase updateUseCase;

    private Restaurant createRestaurant() {
        return Restaurant.newRestaurant(
            "Tia Nicole",
            10,
            "Rua das Clarisas, 100",
            "Belo Horizonte",
            "MG",
            RestaurantDTO.TypeEnum.BRAZILIAN,
            LocalTime.of(10, 0),
            LocalTime.of(22, 0)
        );
    }

    @Test
    void shouldCreateRestaurant() {
        // Arrange
        var restaurant = createRestaurant();
        var input = new RestaurantCreateUseCaseInput(
            restaurant.getName(),
            restaurant.getNumberOfTables(),
            restaurant.getAddress(),
            restaurant.getCity(),
            restaurant.getState(),
            restaurant.getType(),
            restaurant.getOpenedAt(),
            restaurant.getClosedAt() 
        );
    
        when(restaurantRepository.create(any())).thenReturn(restaurant);
    
        // Act
        var output = createUseCase.execute(input);
    
        // Assert
        assertThat(output).isNotNull();
        assertThat(output.name()).isEqualTo(restaurant.getName());
        verify(restaurantRepository, times(1)).create(any());
    }
    

    @Test
    void shouldDeleteRestaurant() {
        // Arrange
        String restaurantId = BaseId.generate().toString();
        var restaurant = createRestaurant();
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(restaurant));
        doNothing().when(restaurantRepository).delete(restaurantId);

        // Act
        deleteUseCase.execute(restaurantId);

        // Assert
        verify(restaurantRepository, times(1)).delete(restaurantId);
    }

    @Test
    void shouldThrowExceptionWhenRestaurantNotFoundForDelete() {
        // Arrange
        String restaurantId = "non-existent-id";
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> deleteUseCase.execute(restaurantId))
            .isInstanceOf(CommonException.class)
            .hasMessage("Restaurant with ID non-existent-id not found.");
    }

    @Test
    void shouldGetRestaurantById() {
        // Arrange
        String restaurantId = BaseId.generate().toString();
        var restaurant = createRestaurant();
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(restaurant));

        // Act
        var output = getByIdUseCase.execute(restaurantId);

        // Assert
        assertThat(output).isNotNull();
        assertThat(output.name()).isEqualTo("Tia Nicole");
        verify(restaurantRepository, times(1)).findById(restaurantId);
    }

    @Test
    void shouldThrowExceptionWhenRestaurantNotFoundForGetById() {
        // Arrange
        String restaurantId = "non-existent-id";
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> getByIdUseCase.execute(restaurantId))
            .isInstanceOf(CommonException.class)
            .hasMessage("Restaurant with ID non-existent-id not found.");
    }

    @Test
    void shouldListRestaurants() {
        // Arrange
        var restaurant = createRestaurant();
        when(restaurantRepository.findAll()).thenReturn(List.of(restaurant));

        // Act
        var outputs = listUseCase.execute();

        // Assert
        assertThat(outputs).isNotEmpty();
        assertThat(outputs.get(0).name()).isEqualTo("Tia Nicole");
        verify(restaurantRepository, times(1)).findAll();
    }

    @Test
    void shouldUpdateRestaurant() {
        // Arrange
        String restaurantId = BaseId.generate().toString();
        var input = new RestaurantUpdateUseCaseInput(
            restaurantId,
            "Tia Nicole Updated",
            15,
            "Rua Nova, 200",
            "São Paulo",
            "SP",
            RestaurantDTO.TypeEnum.ITALIAN,
            LocalTime.of(12, 0),
            LocalTime.of(23, 0)
        );

        var restaurant = createRestaurant();
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(restaurant));
        when(restaurantRepository.update(any())).thenReturn(restaurant);

        // Act
        var output = updateUseCase.execute(input);

        // Assert
        assertThat(output).isNotNull();
        assertThat(output.name()).isEqualTo("Tia Nicole Updated");
        verify(restaurantRepository, times(1)).findById(restaurantId);
        verify(restaurantRepository, times(1)).update(any());
    }

    @Test
    void shouldThrowExceptionWhenRestaurantNotFoundForUpdate() {
        // Arrange
        String restaurantId = "non-existent-id";
        var input = new RestaurantUpdateUseCaseInput(
            restaurantId,
            "Tia Nicole Updated",
            15,
            "Rua Nova, 200",
            "São Paulo",
            "SP",
            RestaurantDTO.TypeEnum.ITALIAN,
            LocalTime.of(12, 0),
            LocalTime.of(23, 0)
        );
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> updateUseCase.execute(input))
            .isInstanceOf(CommonException.class)
            .hasMessage("Restaurant with ID non-existent-id not found.");
    }
}