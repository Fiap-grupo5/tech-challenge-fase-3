package com.g5.restaurants.aplication.infrastructure.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.g5.restaurant.model.CreateRestaurantDTO;
import com.g5.restaurant.model.PaginateRestaurantDTO;
import com.g5.restaurant.model.RestaurantDTO;
import com.g5.restaurant.model.UpdateRestaurantDTO;
import com.g5.restaurants.aplication.domain.base.BaseId;
import com.g5.restaurants.aplication.usecases.restaurant.create.RestaurantCreateUseCase;
import com.g5.restaurants.aplication.usecases.restaurant.create.RestaurantCreateUseCaseInput;
import com.g5.restaurants.aplication.usecases.restaurant.create.RestaurantCreateUseCaseOutput;
import com.g5.restaurants.aplication.usecases.restaurant.delete.RestaurantDeleteUseCase;
import com.g5.restaurants.aplication.usecases.restaurant.retrive.get.RestaurantGetByIdUseCase;
import com.g5.restaurants.aplication.usecases.restaurant.retrive.get.RestaurantGetByIdUseCaseOutput;
import com.g5.restaurants.aplication.usecases.restaurant.retrive.list.RestaurantListUseCase;
import com.g5.restaurants.aplication.usecases.restaurant.retrive.list.RestaurantListUseCaseOutput;
import com.g5.restaurants.aplication.usecases.restaurant.update.RestaurantUpdateUseCase;
import com.g5.restaurants.aplication.usecases.restaurant.update.RestaurantUpdateUseCaseInput;
import com.g5.restaurants.aplication.usecases.restaurant.update.RestaurantUpdateUseCaseOutput;
import com.g5.restaurants.infrastructure.api.RestaurantController;
import com.g5.restaurants.infrastructure.mappers.RestaurantMapper;

class RestaurantControllerTest {

    @InjectMocks
    private RestaurantController restaurantController;

    @Mock
    private RestaurantCreateUseCase restaurantCreateUseCase;

    @Mock
    private RestaurantDeleteUseCase restaurantDeleteUseCase;

    @Mock
    private RestaurantListUseCase restaurantListUseCase;

    @Mock
    private RestaurantGetByIdUseCase restaurantGetByIdUseCase;

    @Mock
    private RestaurantUpdateUseCase restaurantUpdateUseCase;

    @Mock
    private RestaurantMapper restaurantMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateRestaurant() {
        // Arrange
        CreateRestaurantDTO createDTO = new CreateRestaurantDTO()
                .name("Tia Nicole")
                .numberOfTables(10)
                .address("Rua das Clarisas, 100")
                .city("Belo Horizonte")
                .state("MG")
                .type(CreateRestaurantDTO.TypeEnum.BRAZILIAN)
                .openedAt("10:00:00")
                .closedAt("22:00:00");
    
        var useCaseInput = new RestaurantCreateUseCaseOutput(
                new BaseId(UUID.randomUUID().toString()),
                "Tia Nicole",
                10,
                "Rua das Clarisas, 100",
                "Belo Horizonte",
                "MG",
                RestaurantDTO.TypeEnum.BRAZILIAN,
                LocalTime.parse("10:00:00"),
                LocalTime.parse("22:00:00")
        );
    
        RestaurantDTO responseDTO = new RestaurantDTO()
        .id(UUID.fromString(useCaseInput.id().value()))
        .name("Tia Nicole")
        .numberOfTables(10)
        .address("Rua das Clarisas, 100")
        .city("Belo Horizonte")
        .state("MG")
        .type(RestaurantDTO.TypeEnum.BRAZILIAN)
        .openedAt("10:00:00")
        .closedAt("22:00:00");
    
        when(restaurantCreateUseCase.execute(any(RestaurantCreateUseCaseInput.class)))
        .thenReturn(useCaseInput);
        when(restaurantCreateUseCase.execute(any())).thenReturn(useCaseInput);
        when(restaurantMapper.toDTO(useCaseInput)).thenReturn(responseDTO);
    
        // Act
        ResponseEntity<RestaurantDTO> response = restaurantController.createRestaurant(createDTO);
    
        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(responseDTO);
        assertThat(response.getHeaders().getLocation()).isEqualTo(URI.create("/restaurantes/" + useCaseInput.id().value()));
    }

    @Test
    void shouldFindRestaurantById() {
        // Arrange
        var id = BaseId.generate();
        var openedAt = LocalTime.parse("10:00:00");
        var closedAt = LocalTime.parse("22:00:00");
    
        var useCaseOutput = new RestaurantGetByIdUseCaseOutput(
                id, "Tia Nicole", 10, "Rua das Clarisas, 100", "Belo Horizonte", "MG",
                RestaurantDTO.TypeEnum.BRAZILIAN, openedAt, closedAt
        );
    
        // Valor esperado do DTO
        RestaurantDTO expectedDTO = new RestaurantDTO()
                .id(UUID.fromString(id.value()))
                .name("Tia Nicole")
                .numberOfTables(10)
                .address("Rua das Clarisas, 100")
                .city("Belo Horizonte")
                .state("MG")
                .type(RestaurantDTO.TypeEnum.BRAZILIAN)
                .openedAt("10:00:00")
                .closedAt("22:00:00");
    
        when(restaurantGetByIdUseCase.execute(id.value())).thenReturn(useCaseOutput);
        when(restaurantMapper.toDTO(useCaseOutput)).thenReturn(expectedDTO);
    
        // Act
        ResponseEntity<RestaurantDTO> response = restaurantController.findRestaurantById(id.value());
    
        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expectedDTO);
    }

    @Test
    void shouldFindAllRestaurants() {
        // Arrange
        var restaurantList = List.of(
                new RestaurantListUseCaseOutput(
                        BaseId.generate(),
                        "Tia Nicole",
                        10,
                        "Rua das Clarisas, 100",
                        "Belo Horizonte",
                        "MG",
                        RestaurantDTO.TypeEnum.BRAZILIAN,
                        LocalTime.parse("10:00:00"),
                        LocalTime.parse("22:00:00")
                ),
                new RestaurantListUseCaseOutput(
                        BaseId.generate(),
                        "Tia Joana",
                        8,
                        "Rua das Hortênsias, 200",
                        "São Paulo",
                        "SP",
                        RestaurantDTO.TypeEnum.ITALIAN,
                        LocalTime.parse("11:00:00"),
                        LocalTime.parse("23:00:00")
                )
        );
    
        PaginateRestaurantDTO paginatedDTO = new PaginateRestaurantDTO();
        paginatedDTO.addAll(restaurantList.stream()
                .map(output -> new RestaurantDTO()
                        .id(UUID.fromString(output.id().value()))
                        .name(output.name())
                        .numberOfTables(output.numberOfTables())
                        .address(output.address())
                        .city(output.city())
                        .state(output.state())
                        .type(output.type())
                        .openedAt(output.openedAt().toString())
                        .closedAt(output.closedAt().toString())
                )
                .toList());
    
        when(restaurantListUseCase.execute()).thenReturn(restaurantList);
    
        // Act
        ResponseEntity<PaginateRestaurantDTO> response = restaurantController.searchRestaurant(null, null, null);
    
        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(paginatedDTO);
    }

    @Test
    void shouldUpdateRestaurant() {
        // Arrange
        var id = UUID.randomUUID().toString();
        UpdateRestaurantDTO updateDTO = new UpdateRestaurantDTO()
                .name("Updated Name")
                .numberOfTables(10)
                .address("Rua das Flores, 123")
                .city("São Paulo")
                .state("SP")
                .type(UpdateRestaurantDTO.TypeEnum.BRAZILIAN)
                .openedAt("10:00:00")
                .closedAt("22:00:00");

        var useCaseOutput = new RestaurantUpdateUseCaseOutput(
                BaseId.from(id),
                "Updated Name",
                10,
                "Rua das Flores, 123",
                "São Paulo",
                "SP",
                RestaurantDTO.TypeEnum.BRAZILIAN,
                LocalTime.parse("10:00:00"),
                LocalTime.parse("22:00:00")
        );

        var responseDTO = new RestaurantDTO()
                .id(UUID.fromString(id))
                .name("Updated Name")
                .numberOfTables(10)
                .address("Rua das Flores, 123")
                .city("São Paulo")
                .state("SP")
                .type(RestaurantDTO.TypeEnum.BRAZILIAN)
                .openedAt("10:00:00")
                .closedAt("22:00:00");

        when(restaurantUpdateUseCase.execute(any(RestaurantUpdateUseCaseInput.class)))
        .thenReturn(useCaseOutput);
        when(restaurantUpdateUseCase.execute(any())).thenReturn(useCaseOutput);
        when(restaurantMapper.toDTO(useCaseOutput)).thenReturn(responseDTO);

        // Act
        ResponseEntity<RestaurantDTO> response = restaurantController.updateRestaurant(id, updateDTO);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(responseDTO);
    }

    @Test
    void shouldDeleteRestaurant() {
        // Arrange
        var id = UUID.randomUUID().toString();
        doNothing().when(restaurantDeleteUseCase).execute(id);

        // Act
        ResponseEntity<Void> response = restaurantController.deleteRestaurant(id);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(restaurantDeleteUseCase, times(1)).execute(id);
    }
}
