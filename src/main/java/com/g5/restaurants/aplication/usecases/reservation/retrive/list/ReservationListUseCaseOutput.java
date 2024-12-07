package com.g5.restaurants.aplication.usecases.reservation.retrive.list;

import com.g5.reservation.model.ReservationDTO;
import com.g5.restaurants.aplication.domain.base.BaseId;
import com.g5.restaurants.aplication.domain.reservation.Reservation;

import java.time.LocalDate;

public record ReservationListUseCaseOutput(BaseId id, BaseId restaurantId, String customerName, String customerContact, LocalDate reservationDate, Integer numberOfTables, ReservationDTO.StatusEnum status) {
    public static ReservationListUseCaseOutput from(final Reservation reservation) {
        return new ReservationListUseCaseOutput(
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
