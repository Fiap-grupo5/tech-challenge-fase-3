package com.g5.restaurants.aplication.usecases.reservation.retrive.list;

import com.g5.restaurants.aplication.repositories.ReservationRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DefaultReservationListUseCase extends ReservationListUseCase {
    private final ReservationRepository reservationRepository;

    @Override
    public List<ReservationListUseCaseOutput> execute() {
        return reservationRepository.findAll().stream().map(ReservationListUseCaseOutput::from).collect(Collectors.toList());
    }
}
