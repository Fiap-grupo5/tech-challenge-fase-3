package com.g5.restaurants.infrastructure.mappers;

import com.g5.reservation.model.CreateReservationDTO;
import com.g5.reservation.model.ReservationDTO;
import com.g5.reservation.model.UpdateReservationDTO;
import com.g5.restaurants.aplication.domain.base.BaseId;
import com.g5.restaurants.aplication.domain.reservation.Reservation;
import com.g5.restaurants.aplication.usecases.reservation.create.ReservationCreateUseCaseInput;
import com.g5.restaurants.aplication.usecases.reservation.create.ReservationCreateUseCaseOutput;
import com.g5.restaurants.aplication.usecases.reservation.retrive.get.ReservationGetByIdUseCaseOutput;
import com.g5.restaurants.aplication.usecases.reservation.retrive.list.ReservationListUseCaseOutput;
import com.g5.restaurants.aplication.usecases.reservation.update.ReservationUpdateUseCaseInput;
import com.g5.restaurants.aplication.usecases.reservation.update.ReservationUpdateUseCaseOutput;
import com.g5.restaurants.infrastructure.persistence.entities.ReservationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring",
        imports = {BaseId.class})
public interface ReservationMapper {
    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

    @Mapping(target = "id", expression = "java(java.util.UUID.fromString(output.id().toString()))")
    @Mapping(target = "restaurantId", expression = "java(java.util.UUID.fromString(output.restaurantId().toString()))")
    ReservationDTO toDTO(ReservationCreateUseCaseOutput output);

    @Mapping(target = "id", expression = "java(java.util.UUID.fromString(output.id().toString()))")
    @Mapping(target = "restaurantId", expression = "java(java.util.UUID.fromString(output.restaurantId().toString()))")
    ReservationDTO toDTO(ReservationListUseCaseOutput output);

    @Mapping(target = "id", expression = "java(java.util.UUID.fromString(output.id().toString()))")
    @Mapping(target = "restaurantId", expression = "java(java.util.UUID.fromString(output.restaurantId().toString()))")
    ReservationDTO toDTO(ReservationGetByIdUseCaseOutput output);

    @Mapping(target = "id", expression = "java(java.util.UUID.fromString(output.id().toString()))")
    @Mapping(target = "restaurantId", expression = "java(java.util.UUID.fromString(output.restaurantId().toString()))")
    ReservationDTO toDTO(ReservationUpdateUseCaseOutput output);

    ReservationCreateUseCaseInput fromDTO(CreateReservationDTO dto);

    ReservationUpdateUseCaseInput fromDTO(String id, UpdateReservationDTO dto);

    @Mapping(target = "id", expression = "java(new BaseId(entity.getId()))")
    @Mapping(target = "restaurantId", expression = "java(new BaseId(entity.getRestaurantId()))")
    Reservation toObject(ReservationEntity entity);
}