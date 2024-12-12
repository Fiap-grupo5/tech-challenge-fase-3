package com.g5.restaurants.aplication.usecases.reservation.create;

import com.g5.reservation.model.ReservationDTO;
import com.g5.restaurants.aplication.domain.base.BaseId;
import com.g5.restaurants.aplication.domain.reservation.Reservation;

import java.time.LocalDate;

public record ReservationCreateUseCaseOutput(BaseId id, BaseId restaurantId, String customerName, String customerContact, LocalDate reservationDate, Integer numberOfTables, ReservationDTO.StatusEnum status) {
    public static ReservationCreateUseCaseOutput from(final Reservation reservation) {
        return new ReservationCreateUseCaseOutput(
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
