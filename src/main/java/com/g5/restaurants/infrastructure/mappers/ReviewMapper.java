package com.g5.restaurants.infrastructure.mappers;

import com.g5.restaurants.aplication.domain.base.BaseId;
import com.g5.restaurants.aplication.domain.review.Review;
import com.g5.restaurants.aplication.usecases.review.create.ReviewCreateUseCaseInput;
import com.g5.restaurants.aplication.usecases.review.create.ReviewCreateUseCaseOutput;
import com.g5.restaurants.aplication.usecases.review.retrieve.get.ReviewGetByIdUseCaseOutput;
import com.g5.restaurants.aplication.usecases.review.retrieve.list.ReviewListUseCaseOutput;
import com.g5.restaurants.aplication.usecases.review.retrieve.list.byRestaurantId.ReviewListByRestaurantIdUseCaseInput;
import com.g5.restaurants.aplication.usecases.review.retrieve.list.byRestaurantId.ReviewListByRestaurantIdUseCaseOutput;
import com.g5.restaurants.aplication.usecases.review.update.ReviewUpdateUseCaseInput;
import com.g5.restaurants.aplication.usecases.review.update.ReviewUpdateUseCaseOutput;
import com.g5.restaurants.infrastructure.persistence.entities.ReviewEntity;
import com.g5.review.model.CreateReviewDTO;
import com.g5.review.model.ReviewDTO;
import com.g5.review.model.UpdateReviewDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(
        componentModel = "spring",
        imports = {BaseId.class})
public interface ReviewMapper {

    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    // Métodos toDTO únicos para cada caso de uso
    @Mapping(target = "id", expression = "java(java.util.UUID.fromString(output.id().toString()))")
    @Mapping(target = "restaurantId", expression = "java(java.util.UUID.fromString(output.restaurantId().toString()))")
    @Mapping(target = "createdAt", expression = "java(mapOffsetDateTime(output.createdAt()))")
    @Mapping(target = "updatedAt", expression = "java(mapOffsetDateTime(output.updatedAt()))")
    ReviewDTO toDTOFromCreateOutput(ReviewCreateUseCaseOutput output);

    @Mapping(target = "id", expression = "java(java.util.UUID.fromString(output.id().toString()))")
    @Mapping(target = "restaurantId", expression = "java(java.util.UUID.fromString(output.restaurantId().toString()))")
    @Mapping(target = "createdAt", expression = "java(mapOffsetDateTime(output.createdAt()))")
    @Mapping(target = "updatedAt", expression = "java(mapOffsetDateTime(output.updatedAt()))")
    ReviewDTO toDTOFromGetByIdOutput(ReviewGetByIdUseCaseOutput output);

    @Mapping(target = "id", expression = "java(java.util.UUID.fromString(output.id().toString()))")
    @Mapping(target = "restaurantId", expression = "java(java.util.UUID.fromString(output.restaurantId().toString()))")
    @Mapping(target = "createdAt", expression = "java(mapOffsetDateTime(output.createdAt()))")
    @Mapping(target = "updatedAt", expression = "java(mapOffsetDateTime(output.updatedAt()))")
    ReviewDTO toDTOFromListOutput(ReviewListUseCaseOutput output);

    @Mapping(target = "id", expression = "java(java.util.UUID.fromString(output.id().toString()))")
    @Mapping(target = "restaurantId", expression = "java(java.util.UUID.fromString(output.restaurantId().toString()))")
    @Mapping(target = "createdAt", expression = "java(mapOffsetDateTime(output.createdAt()))")
    @Mapping(target = "updatedAt", expression = "java(mapOffsetDateTime(output.updatedAt()))")
    ReviewDTO toDTOFromListByRestaurantOutput(ReviewListByRestaurantIdUseCaseOutput output);

    @Mapping(target = "id", expression = "java(java.util.UUID.fromString(output.id().toString()))")
    @Mapping(target = "restaurantId", expression = "java(java.util.UUID.fromString(output.restaurantId().toString()))")
    @Mapping(target = "createdAt", expression = "java(mapOffsetDateTime(output.createdAt()))")
    @Mapping(target = "updatedAt", expression = "java(mapOffsetDateTime(output.updatedAt()))")
    ReviewDTO toDTOFromUpdateOutput(ReviewUpdateUseCaseOutput output);

    // Outros métodos
    ReviewCreateUseCaseInput fromDTO(CreateReviewDTO dto);

    ReviewListByRestaurantIdUseCaseInput fromDTO(String restaurantId);

    ReviewUpdateUseCaseInput fromDTO(String id, UpdateReviewDTO dto);

    @Mapping(target = "id", expression = "java(new BaseId(entity.getId()))")
    @Mapping(target = "restaurantId", expression = "java(new BaseId(entity.getRestaurantId()))")
    Review toObject(ReviewEntity entity);

    default OffsetDateTime mapOffsetDateTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.atOffset(ZoneOffset.UTC);
    }
}
