package com.g5.restaurants.aplication.domain.restaurant;

import java.time.LocalTime;
import java.util.Objects;

import com.g5.restaurant.model.RestaurantDTO;
import com.g5.restaurants.aplication.domain.base.BaseId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
        if (numberOfTables == null || numberOfTables < 1) {
            throw new IllegalArgumentException("Number of tables must be at least 1");
        }
        var id = BaseId.generate();
        return new Restaurant(id, name, numberOfTables, address, city, state, type, openedAt, closedAt);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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
