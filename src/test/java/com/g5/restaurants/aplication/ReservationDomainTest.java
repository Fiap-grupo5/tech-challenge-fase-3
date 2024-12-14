package com.g5.restaurants.aplication;

import com.g5.reservation.model.ReservationDTO;
import com.g5.restaurants.aplication.domain.base.BaseId;
import com.g5.restaurants.aplication.domain.reservation.Reservation;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReservationDomainTest {

    @Test
    void shouldCreateNewReservation() {
        // Arrange
        var restaurantId = BaseId.generate();
        var customerName = "John Doe";
        var customerContact = "(11) 98765-4321"; // Formato válido
        var reservationDate = LocalDate.of(2024, 12, 25);
        var numberOfTables = 3;
        var status = ReservationDTO.StatusEnum.PENDING;

        // Act
        var reservation = Reservation.newReservation(
            restaurantId,
            customerName,
            customerContact,
            reservationDate,
            numberOfTables,
            status
        );

        // Assert
        assertThat(reservation).isNotNull();
        assertThat(reservation.getId()).isNotNull();
        assertThat(reservation.getRestaurantId()).isEqualTo(restaurantId);
        assertThat(reservation.getCustomerName()).isEqualTo(customerName);
        assertThat(reservation.getCustomerContact()).isEqualTo(customerContact);
        assertThat(reservation.getReservationDate()).isEqualTo(reservationDate);
        assertThat(reservation.getNumberOfTables()).isEqualTo(numberOfTables);
        assertThat(reservation.getStatus()).isEqualTo(status);
    }

    @Test
    void shouldUpdateReservationStatus() {
        // Arrange
        var reservation = new Reservation(
            BaseId.generate(),
            BaseId.generate(),
            "Jane Doe",
            "(11) 98765-4321", // Formato válido
            LocalDate.of(2024, 12, 25),
            3,
            ReservationDTO.StatusEnum.PENDING
        );

        var newStatus = ReservationDTO.StatusEnum.CONFIRMED;

        // Act
        reservation.update(newStatus);

        // Assert
        assertThat(reservation.getStatus()).isEqualTo(newStatus);
    }

    @Test
    void shouldThrowExceptionWhenCustomerNameIsNull() {
        // Arrange
        var restaurantId = BaseId.generate();
        var customerContact = "(11) 98765-4321";
        var reservationDate = LocalDate.of(2024, 12, 25);
        var numberOfTables = 3;
        var status = ReservationDTO.StatusEnum.PENDING;

        // Act & Assert
        assertThatThrownBy(() -> Reservation.newReservation(
            restaurantId,
            null,
            customerContact,
            reservationDate,
            numberOfTables,
            status
        )).isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Customer name cannot be null or empty");
    }

    @Test
    void shouldThrowExceptionWhenNumberOfTablesIsInvalid() {
        // Arrange
        var restaurantId = BaseId.generate();
        var customerName = "John Doe";
        var customerContact = "(11) 98765-4321";
        var reservationDate = LocalDate.of(2024, 12, 25);
        var invalidNumberOfTables = 0;
        var status = ReservationDTO.StatusEnum.PENDING;

        // Act & Assert
        assertThatThrownBy(() -> Reservation.newReservation(
            restaurantId,
            customerName,
            customerContact,
            reservationDate,
            invalidNumberOfTables,
            status
        )).isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Number of tables must be at least 1");
    }

    @Test
    void shouldVerifyReservationEquality() {
        // Arrange
        var id = BaseId.generate();
        var reservation1 = new Reservation(
            id,
            BaseId.generate(),
            "John Doe",
            "(11) 98765-4321",
            LocalDate.of(2024, 12, 25),
            3,
            ReservationDTO.StatusEnum.PENDING
        );

        var reservation2 = new Reservation(
            id,
            BaseId.generate(),
            "John Doe",
            "(11) 98765-4321",
            LocalDate.of(2024, 12, 25),
            3,
            ReservationDTO.StatusEnum.PENDING
        );

        // Assert
        assertThat(reservation1).isEqualTo(reservation2);
        assertThat(reservation1.hashCode()).isEqualTo(reservation2.hashCode());
    }

    @Test
    void shouldVerifyToStringMethod() {
        // Arrange
        var reservation = new Reservation(
            BaseId.generate(),
            BaseId.generate(),
            "John Doe",
            "(11) 98765-4321",
            LocalDate.of(2024, 12, 25),
            3,
            ReservationDTO.StatusEnum.PENDING
        );

        // Act
        var result = reservation.toString();

        // Assert
        assertThat(result).contains("John Doe");
        assertThat(result).contains("(11) 98765-4321");
        assertThat(result).contains("PENDING");
    }
}
