package com.g5.restaurants.aplication.usecases.reservation.delete;

import com.g5.restaurants.aplication.exceptions.CommonException;
import com.g5.restaurants.aplication.repositories.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class DefaultReservationDeleteUseCase extends ReservationDeleteUseCase {
    private final ReservationRepository reservationRepository;

    @Override
    public void execute(String id) {
        reservationRepository.findById(id)
                                    .orElseThrow(() ->
                                            new CommonException(
                                                    "Reservation with ID %s not found.".formatted(id),
                                                    HttpStatus.NOT_FOUND
                                            ));
        reservationRepository.delete(id);
    }
}
