package com.g5.restaurants.aplication.usecases.reservation.retrive.get;

import com.g5.restaurants.aplication.exceptions.CommonException;
import com.g5.restaurants.aplication.repositories.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class DefaultReservationGetByIdUseCase extends ReservationGetByIdUseCase {
    private final ReservationRepository reservationRepository;

    @Override
    public ReservationGetByIdUseCaseOutput execute(String input) {
        return reservationRepository.findById(input).map(ReservationGetByIdUseCaseOutput::from).orElseThrow(() ->
                new CommonException("Reservation with ID %s not found.".formatted(input), HttpStatus.NOT_FOUND));
    }
}
