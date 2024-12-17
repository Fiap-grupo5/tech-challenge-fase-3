package com.g5.restaurants.infrastructure.repositories;

import com.g5.restaurants.aplication.domain.reservation.Reservation;
import com.g5.restaurants.aplication.repositories.ReservationRepository;
import com.g5.restaurants.infrastructure.persistence.entities.ReservationEntity;
import com.g5.restaurants.infrastructure.persistence.repositories.ReservationMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepository {
    private final ReservationMongoRepository reservationMongoRepository;

    @Override
    public Reservation create(Reservation reservation) {
        return save(reservation);
    }

    @Override
    public Optional<Reservation> findById(String id) {
        return reservationMongoRepository.findById(id).map(ReservationEntity::toReservation);
    }

    @Override
    public Reservation update(Reservation reservation) {
        return save(reservation);
    }

    @Override
    public List<Reservation> findAll() {
        final var pageResult = reservationMongoRepository.findAll();

        return pageResult.stream().map(ReservationEntity::toReservation).toList();
    }

    @Override
    public void delete(String id) {
        reservationMongoRepository.deleteById(id);
    }

    private Reservation save(final Reservation reservation) {
        return reservationMongoRepository.save(ReservationEntity.of(reservation)).toReservation();
    }
}
