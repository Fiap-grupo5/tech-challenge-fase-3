package com.g5.restaurants.aplication.usecases.reservation.update;

import com.g5.restaurants.aplication.exceptions.CommonException;
import com.g5.restaurants.aplication.repositories.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class DefaultReservationUpdateUseCase extends ReservationUpdateUseCase {
    private final ReservationRepository reservationRepository;

    @Override
    public ReservationUpdateUseCaseOutput execute(ReservationUpdateUseCaseInput input) {
        final var id = input.id();
        final var reservation = reservationRepository.findById(id)
                                    .orElseThrow(()->
                                            new CommonException(
                                                    "Reservation with ID %s not found!".formatted(id),
                                                    HttpStatus.NOT_FOUND));

        reservation.update(input.status());

        return ReservationUpdateUseCaseOutput.from(reservationRepository.update(reservation));
    }
}
