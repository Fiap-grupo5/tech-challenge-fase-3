package com.g5.restaurants.aplication.repositories;

import com.g5.restaurants.aplication.domain.reservation.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository {
    Reservation create(Reservation reservation);
    Optional<Reservation> findById(String id);
    Reservation update(Reservation reservation);
    List<Reservation> findAll();
    void delete(String id);
}
