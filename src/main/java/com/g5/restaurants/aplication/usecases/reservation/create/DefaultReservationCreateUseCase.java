package com.g5.restaurants.aplication.usecases.reservation.create;

import com.g5.reservation.model.ReservationDTO;
import com.g5.restaurants.aplication.domain.base.BaseId;
import com.g5.restaurants.aplication.domain.reservation.Reservation;
import com.g5.restaurants.aplication.repositories.ReservationRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultReservationCreateUseCase extends ReservationCreateUseCase {
    private final ReservationRepository reservationRepository;

    @Override
    public ReservationCreateUseCaseOutput execute(ReservationCreateUseCaseInput input) {
        final var reservation =
                Reservation.newReservation(
                        BaseId.from(input.restaurantId()),
                        input.customerName(),
                        input.customerContact(),
                        input.reservationDate(),
                        input.numberOfTables(),
                        ReservationDTO.StatusEnum.PENDING);
        return ReservationCreateUseCaseOutput.from(reservationRepository.create(reservation));
    }
}
