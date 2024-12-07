package com.g5.restaurants.aplication.domain.restaurant;

import com.g5.restaurant.model.RestaurantDTO;
import com.g5.restaurants.aplication.domain.base.BaseId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
    private BaseId id;
    private String name;
    private Integer numberOfTables;
    private String address;
    private String city;
    private String state;
    private RestaurantDTO.TypeEnum type;
    private LocalTime openedAt;
    private LocalTime closedAt;

    public static Restaurant newRestaurant(String name, Integer numberOfTables, String address, String city, String state, RestaurantDTO.TypeEnum type, LocalTime openedAt, LocalTime closedAt) {
        var id = BaseId.generate();
        return new Restaurant(id, name, numberOfTables, address, city, state, type, openedAt, closedAt);
    }

    public void update(String name, Integer numberOfTables, String address, String city, String state, RestaurantDTO.TypeEnum type, LocalTime openedAt, LocalTime closedAt) {
        this.name = name;
        this.numberOfTables = numberOfTables;
        this.address = address;
        this.city = city;
        this.state = state;
        this.type = type;
        this.openedAt = openedAt;
        this.closedAt = closedAt;
    }
}
