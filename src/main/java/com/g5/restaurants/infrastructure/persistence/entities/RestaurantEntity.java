package com.g5.restaurants.infrastructure.persistence.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.g5.restaurants.aplication.domain.restaurant.Restaurant;
import com.g5.restaurants.infrastructure.mappers.RestaurantMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "restaurant")
public class RestaurantEntity {
    @Id
    private String id;

    private String name;

    private Integer numberOfTables;

    private String address;

    private String city;

    private String state;

    private String type;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime openedAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime closedAt;

    public static RestaurantEntity of(Restaurant restaurant) {
        return new RestaurantEntity(
                restaurant.getId().toString(),
                restaurant.getName(),
                restaurant.getNumberOfTables(),
                restaurant.getAddress(),
                restaurant.getCity(),
                restaurant.getState(),
                restaurant.getType().toString(),
                restaurant.getOpenedAt(),
                restaurant.getClosedAt()
        );
    }

    public Restaurant toRestaurant() {
        return RestaurantMapper.INSTANCE.toObject( this);
    }
}
