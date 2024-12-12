package com.g5.restaurants.aplication.usecases.reservation.retrive.get;

import com.g5.reservation.model.ReservationDTO;
import com.g5.restaurants.aplication.domain.base.BaseId;
import com.g5.restaurants.aplication.domain.reservation.Reservation;

import java.time.LocalDate;

public record ReservationGetByIdUseCaseOutput(BaseId id, BaseId restaurantId, String customerName, String customerContact, LocalDate reservationDate, Integer numberOfTables, ReservationDTO.StatusEnum status) {
    public static ReservationGetByIdUseCaseOutput from(final Reservation reservation) {
        return new ReservationGetByIdUseCaseOutput(
                reservation.getId(),
                reservation.getRestaurantId(),
                reservation.getCustomerName(),
                reservation.getCustomerContact(),
                reservation.getReservationDate(),
                reservation.getNumberOfTables(),
                reservation.getStatus()
        );
    }
}