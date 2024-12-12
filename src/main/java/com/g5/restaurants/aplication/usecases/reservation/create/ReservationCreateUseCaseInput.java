package com.g5.restaurants.aplication.usecases.reservation.create;

import java.time.LocalDate;

public record ReservationCreateUseCaseInput(String restaurantId, String customerName, String customerContact, LocalDate reservationDate, Integer numberOfTables) {
}
