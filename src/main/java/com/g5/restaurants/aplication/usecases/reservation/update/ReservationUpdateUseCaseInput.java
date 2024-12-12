package com.g5.restaurants.aplication.usecases.reservation.update;

import com.g5.reservation.model.ReservationDTO;

public record ReservationUpdateUseCaseInput(String id, ReservationDTO.StatusEnum status) {
}
