package com.g5.restaurants.infrastructure.mappers;

import com.g5.restaurant.model.CreateRestaurantDTO;
import com.g5.restaurant.model.RestaurantDTO;
import com.g5.restaurant.model.UpdateRestaurantDTO;
import com.g5.restaurants.aplication.domain.base.BaseId;
import com.g5.restaurants.aplication.domain.restaurant.Restaurant;
import com.g5.restaurants.aplication.usecases.restaurant.create.RestaurantCreateUseCaseInput;
import com.g5.restaurants.aplication.usecases.restaurant.create.RestaurantCreateUseCaseOutput;
import com.g5.restaurants.aplication.usecases.restaurant.retrive.get.RestaurantGetByIdUseCaseOutput;
import com.g5.restaurants.aplication.usecases.restaurant.retrive.list.RestaurantListUseCaseOutput;
import com.g5.restaurants.aplication.usecases.restaurant.update.RestaurantUpdateUseCaseInput;
import com.g5.restaurants.aplication.usecases.restaurant.update.RestaurantUpdateUseCaseOutput;
import com.g5.restaurants.infrastructure.persistence.entities.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring",
        imports = {BaseId.class})
public interface RestaurantMapper {

    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    @Mapping(target = "id", expression = "java(java.util.UUID.fromString(output.id().toString()))")
    RestaurantDTO toDTO(RestaurantCreateUseCaseOutput output);

    @Mapping(target = "id", expression = "java(java.util.UUID.fromString(output.id().toString()))")
    RestaurantDTO toDTO(RestaurantGetByIdUseCaseOutput output);

    @Mapping(target = "id", expression = "java(java.util.UUID.fromString(output.id().toString()))")
    RestaurantDTO toDTO(RestaurantListUseCaseOutput output);

    @Mapping(target = "id", expression = "java(java.util.UUID.fromString(output.id().toString()))")
    RestaurantDTO toDTO(RestaurantUpdateUseCaseOutput output);

    RestaurantCreateUseCaseInput fromDTO(CreateRestaurantDTO dto);

    RestaurantUpdateUseCaseInput fromDTO(String id, UpdateRestaurantDTO dto);

    @Mapping(target = "id", expression = "java(new BaseId(entity.getId()))")
    Restaurant toObject(RestaurantEntity entity);
}
