package com.g5.restaurants.aplication.infrastructure.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import com.g5.reservation.model.ReservationDTO;
import com.g5.restaurants.aplication.domain.base.BaseId;
import com.g5.restaurants.aplication.domain.reservation.Reservation;
import com.g5.restaurants.infrastructure.persistence.repositories.ReservationMongoRepository;
import com.g5.restaurants.infrastructure.repositories.ReservationRepositoryImpl;

@DataMongoTest
class ReservationRepositoryImplTest {

    @Autowired
    private ReservationMongoRepository reservationMongoRepository;

    private ReservationRepositoryImpl reservationRepository;

    @BeforeEach
    void setUp() {
        reservationRepository = new ReservationRepositoryImpl(reservationMongoRepository);
        reservationMongoRepository.deleteAll(); // Limpa os dados entre os testes
    }

    @Test
    void shouldCreateReservation() {
        // Arrange
        Reservation reservation = createSampleReservation();

        // Act
        Reservation savedReservation = reservationRepository.create(reservation);

        // Assert
        assertThat(savedReservation).isNotNull();
        assertThat(savedReservation.getId()).isEqualTo(reservation.getId());
        assertThat(savedReservation.getCustomerName()).isEqualTo("John Doe");
    }

    @Test
    void shouldFindReservationById() {
        // Arrange
        Reservation reservation = createSampleReservation();
        reservationRepository.create(reservation);

        // Act
        Optional<Reservation> foundReservation = reservationRepository.findById(reservation.getId().toString());

        // Assert
        assertThat(foundReservation).isPresent();
        assertThat(foundReservation.get().getCustomerName()).isEqualTo("John Doe");
    }

    @Test
    void shouldFindAllReservations() {
        // Arrange
        Reservation reservation1 = createSampleReservation();
        Reservation reservation2 = createSampleReservation("Jane Doe");
        reservationRepository.create(reservation1);
        reservationRepository.create(reservation2);

        // Act
        var reservations = reservationRepository.findAll();

        // Assert
        assertThat(reservations).hasSize(2);
        assertThat(reservations).extracting("customerName").contains("John Doe", "Jane Doe");
    }

    @Test
    void shouldUpdateReservation() {
        // Arrange
        Reservation reservation = createSampleReservation();
        reservationRepository.create(reservation);

        reservation.update(
                "Jane Doe",
                "(21) 91234-5678",
                LocalDate.of(2024, 12, 31),
                5
        );

        // Act
        Reservation updatedReservation = reservationRepository.update(reservation);

        // Assert
        assertThat(updatedReservation.getCustomerName()).isEqualTo("Jane Doe");
        assertThat(updatedReservation.getCustomerContact()).isEqualTo("(21) 91234-5678");
        assertThat(updatedReservation.getReservationDate()).isEqualTo(LocalDate.of(2024, 12, 31));
        assertThat(updatedReservation.getNumberOfTables()).isEqualTo(5);
    }

    @Test
    void shouldDeleteReservationById() {
        // Arrange
        Reservation reservation = createSampleReservation();
        reservationRepository.create(reservation);

        // Act
        reservationRepository.delete(reservation.getId().toString());

        // Assert
        Optional<Reservation> deletedReservation = reservationRepository.findById(reservation.getId().toString());
        assertThat(deletedReservation).isEmpty();
    }

    private Reservation createSampleReservation() {
        return createSampleReservation("John Doe");
    }

    private Reservation createSampleReservation(String customerName) {
        return Reservation.newReservation(
                BaseId.generate(),
                customerName,
                "(11) 98765-4321",
                LocalDate.of(2024, 12, 25),
                3,
                ReservationDTO.StatusEnum.PENDING
        );
    }
}
