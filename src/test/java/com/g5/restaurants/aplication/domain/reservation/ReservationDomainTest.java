package com.g5.restaurants.aplication.domain.reservation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.g5.reservation.model.ReservationDTO;
import com.g5.restaurants.aplication.domain.base.BaseId;

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
    @Test
    void shouldThrowExceptionWhenCustomerNameIsEmpty() {
        // Arrange
        var restaurantId = BaseId.generate();
    
        // Act & Assert
        assertThatThrownBy(() -> Reservation.newReservation(
            restaurantId,
            "   ",
            "(11) 98765-4321",
            LocalDate.of(2024, 12, 25),
            3,
            ReservationDTO.StatusEnum.PENDING
        ))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Customer name cannot be null or empty");
    }
    
    @Test
    void shouldThrowExceptionWhenCustomerContactIsInvalid() {
        // Arrange
        var restaurantId = BaseId.generate();
    
        // Act & Assert
        assertThatThrownBy(() -> Reservation.newReservation(
            restaurantId,
            "John Doe",
            "invalid-contact-format",
            LocalDate.of(2024, 12, 25),
            3,
            ReservationDTO.StatusEnum.PENDING
        ))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Invalid contact format. Expected formats: (DDD) 00000-0000 or (DDD) 0000-0000");
    }
    
    @Test
    void shouldThrowExceptionWhenUpdatingWithEmptyCustomerName() {
        // Arrange
        var reservation = Reservation.newReservation(
            BaseId.generate(),
            "John Doe",
            "(11) 98765-4321",
            LocalDate.of(2024, 12, 25),
            3,
            ReservationDTO.StatusEnum.PENDING
        );
    
        // Act & Assert
        assertThatThrownBy(() -> reservation.update("   ", null, null, null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Customer name cannot be empty");
    }
    
    @Test
    void shouldThrowExceptionWhenUpdatingWithInvalidContact() {
        // Arrange
        var reservation = Reservation.newReservation(
            BaseId.generate(),
            "John Doe",
            "(11) 98765-4321",
            LocalDate.of(2024, 12, 25),
            3,
            ReservationDTO.StatusEnum.PENDING
        );
    
        // Act & Assert
        assertThatThrownBy(() -> reservation.update(null, "invalid-contact", null, null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Invalid contact format. Expected formats: (DDD) 00000-0000 or (DDD) 0000-0000");
    }
    
    @Test
    void shouldThrowExceptionWhenUpdatingWithInvalidNumberOfTables() {
        // Arrange
        var reservation = Reservation.newReservation(
            BaseId.generate(),
            "John Doe",
            "(11) 98765-4321",
            LocalDate.of(2024, 12, 25),
            3,
            ReservationDTO.StatusEnum.PENDING
        );
    
        // Act & Assert
        assertThatThrownBy(() -> reservation.update(null, null, null, 0))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Number of tables must be at least 1");
    }
    
    @Test
    void shouldUpdateReservationDate() {
        // Arrange
        var reservation = Reservation.newReservation(
            BaseId.generate(),
            "John Doe",
            "(11) 98765-4321",
            LocalDate.of(2024, 12, 25),
            3,
            ReservationDTO.StatusEnum.PENDING
        );
    
        // Act
        reservation.update(null, null, LocalDate.of(2025, 1, 1), null);
    
        // Assert
        assertThat(reservation.getReservationDate()).isEqualTo(LocalDate.of(2025, 1, 1));
    }
    
    @Test
    void shouldThrowExceptionWhenUpdatingWithNullStatus() {
        // Arrange
        var reservation = Reservation.newReservation(
            BaseId.generate(),
            "John Doe",
            "(11) 98765-4321",
            LocalDate.of(2024, 12, 25),
            3,
            ReservationDTO.StatusEnum.PENDING
        );
    
        // Act & Assert
        assertThatThrownBy(() -> reservation.update((ReservationDTO.StatusEnum) null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Status cannot be null");
    }
    
    @Test
    void equalsShouldReturnFalseWhenObjectIsNull() {
        // Arrange
        var reservation = Reservation.newReservation(
            BaseId.generate(),
            "John Doe",
            "(11) 98765-4321",
            LocalDate.of(2024, 12, 25),
            3,
            ReservationDTO.StatusEnum.PENDING
        );
    
        // Act
        boolean result = reservation.equals(null);
    
        // Assert
        assertThat(result).isFalse();
    }
    
    @Test
    void equalsShouldReturnFalseWhenObjectIsDifferentType() {
        // Arrange
        var reservation = Reservation.newReservation(
            BaseId.generate(),
            "John Doe",
            "(11) 98765-4321",
            LocalDate.of(2024, 12, 25),
            3,
            ReservationDTO.StatusEnum.PENDING
        );
    
        // Act
        boolean result = reservation.equals("string");
    
        // Assert
        assertThat(result).isFalse();
    }
    
    @Test
    void equalsShouldReturnTrueWhenComparingSameObject() {
        // Arrange
        var reservation = Reservation.newReservation(
            BaseId.generate(),
            "John Doe",
            "(11) 98765-4321",
            LocalDate.of(2024, 12, 25),
            3,
            ReservationDTO.StatusEnum.PENDING
        );
    
        // Act
        boolean result = reservation.equals(reservation);
    
        // Assert
        assertThat(result).isTrue();
    }
    
    @Test
    void shouldThrowExceptionWhenCustomerContactIsNull() {
        // Arrange
        var restaurantId = BaseId.generate();
    
        // Act & Assert
        assertThatThrownBy(() -> Reservation.newReservation(
            restaurantId,
            "John Doe",
            null,
            LocalDate.of(2024, 12, 25),
            3,
            ReservationDTO.StatusEnum.PENDING
        ))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Invalid contact format. Expected formats: (DDD) 00000-0000 or (DDD) 0000-0000");
    }
    
    @Test
    void shouldThrowExceptionWhenNumberOfTablesIsNull() {
        // Arrange
        var restaurantId = BaseId.generate();
    
        // Act & Assert
        assertThatThrownBy(() -> Reservation.newReservation(
            restaurantId,
            "John Doe",
            "(11) 98765-4321",
            LocalDate.of(2024, 12, 25),
            null,
            ReservationDTO.StatusEnum.PENDING
        ))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Number of tables must be at least 1");
    }
    
    @Test
    void shouldCreateNewReservationWithNullDateAndThenUpdateIt() {
        // Arrange
        var restaurantId = BaseId.generate();
        var reservation = Reservation.newReservation(
            restaurantId,
            "John Doe",
            "(11) 98765-4321",
            null,
            3,
            ReservationDTO.StatusEnum.PENDING
        );
    
        // Act
        reservation.update(null, null, LocalDate.of(2025,1,1), null);
    
        // Assert
        assertThat(reservation.getReservationDate()).isEqualTo(LocalDate.of(2025,1,1));
    }
    
    @Test
    void shouldNotUpdateReservationDateWhenItIsNull() {
        // Arrange
        var reservation = Reservation.newReservation(
            BaseId.generate(),
            "John Doe",
            "(11) 98765-4321",
            LocalDate.of(2024, 12, 25),
            3,
            ReservationDTO.StatusEnum.PENDING
        );
    
        // Act
        reservation.update(null, null, null, null);
    
        // Assert
        assertThat(reservation.getReservationDate()).isEqualTo(LocalDate.of(2024, 12, 25));
    }
    
}
