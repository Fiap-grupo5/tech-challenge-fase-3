package com.g5.restaurants.infrastructure.api;

import com.g5.restaurant.api.RestaurantApi;
import com.g5.restaurant.model.CreateRestaurantDTO;
import com.g5.restaurant.model.PaginateRestaurantDTO;
import com.g5.restaurant.model.RestaurantDTO;
import com.g5.restaurant.model.UpdateRestaurantDTO;
import com.g5.restaurants.aplication.usecases.restaurant.create.RestaurantCreateUseCase;
import com.g5.restaurants.aplication.usecases.restaurant.delete.RestaurantDeleteUseCase;
import com.g5.restaurants.aplication.usecases.restaurant.retrive.get.RestaurantGetByIdUseCase;
import com.g5.restaurants.aplication.usecases.restaurant.retrive.list.RestaurantListUseCase;
import com.g5.restaurants.aplication.usecases.restaurant.update.RestaurantUpdateUseCase;
import com.g5.restaurants.infrastructure.mappers.RestaurantMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class RestaurantController implements RestaurantApi {
    private static final RestaurantMapper restaurantMapper = RestaurantMapper.INSTANCE;
    private final RestaurantGetByIdUseCase restaurantGetByIdUseCase;
    private final RestaurantListUseCase restaurantListUseCase;
    private final RestaurantCreateUseCase restaurantCreateUseCase;
    private final RestaurantUpdateUseCase restaurantUpdateUseCase;
    private final RestaurantDeleteUseCase restaurantDeleteUseCase;

    @Override
    public ResponseEntity<RestaurantDTO> findRestaurantById(String id) {
        final var output = restaurantMapper.toDTO(restaurantGetByIdUseCase.execute(id));
        return ResponseEntity.ok(output);
    }

    @Override
    public ResponseEntity<PaginateRestaurantDTO> searchRestaurant(
            String city,
            String state,
            String type) {
        final var restaurant = restaurantListUseCase.execute().stream().map(restaurantMapper::toDTO).collect(Collectors.toList());
        final var paginatedRestaurant = new PaginateRestaurantDTO();
        paginatedRestaurant.addAll(restaurant);
        return ResponseEntity.ok(paginatedRestaurant);
    }

    @Override
    public ResponseEntity<RestaurantDTO> createRestaurant(final CreateRestaurantDTO body) {
        final var useCaseInput = restaurantMapper.fromDTO(body);
        final var useCaseOutput = restaurantCreateUseCase.execute(useCaseInput);
        var uri = URI.create("/restaurantes/" + useCaseOutput.id());

        return ResponseEntity.created(uri).body(restaurantMapper.toDTO(useCaseOutput));
    }

    @Override
    public ResponseEntity<RestaurantDTO> updateRestaurant(
            String id, UpdateRestaurantDTO body) {
        final var input = restaurantMapper.fromDTO(id, body);
        final var output = restaurantMapper.toDTO(restaurantUpdateUseCase.execute(input));
        return ResponseEntity.ok(output);
    }

    @Override
    public ResponseEntity<Void> deleteRestaurant(String id) {
        restaurantDeleteUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
