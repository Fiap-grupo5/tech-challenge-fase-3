package com.g5.restaurants.aplication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.g5.reservation.model.ReservationDTO;
import com.g5.restaurants.aplication.domain.base.BaseId;
import com.g5.restaurants.aplication.domain.reservation.Reservation;
import com.g5.restaurants.aplication.exceptions.CommonException;
import com.g5.restaurants.aplication.repositories.ReservationRepository;
import com.g5.restaurants.aplication.usecases.reservation.create.DefaultReservationCreateUseCase;
import com.g5.restaurants.aplication.usecases.reservation.create.ReservationCreateUseCaseInput;
import com.g5.restaurants.aplication.usecases.reservation.delete.DefaultReservationDeleteUseCase;
import com.g5.restaurants.aplication.usecases.reservation.retrive.get.DefaultReservationGetByIdUseCase;
import com.g5.restaurants.aplication.usecases.reservation.retrive.list.DefaultReservationListUseCase;
import com.g5.restaurants.aplication.usecases.reservation.update.DefaultReservationUpdateUseCase;
import com.g5.restaurants.aplication.usecases.reservation.update.ReservationUpdateUseCaseInput;


@ExtendWith(MockitoExtension.class)
public class ReservationUseCaseTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private DefaultReservationCreateUseCase createUseCase;

    @InjectMocks
    private DefaultReservationDeleteUseCase deleteUseCase;

    @InjectMocks
    private DefaultReservationGetByIdUseCase getByIdUseCase;

    @InjectMocks
    private DefaultReservationListUseCase listUseCase;

    @InjectMocks
    private DefaultReservationUpdateUseCase updateUseCase;

    private Reservation createReservation() {
        return Reservation.newReservation(
            BaseId.generate(),
            "Customer Name",
            "(11) 98765-4321",
            LocalDate.now(),
            2,
            ReservationDTO.StatusEnum.CONFIRMED
        );
    }

    @Test
    void shouldCreateReservation() {
        // Arrange
        var input = new ReservationCreateUseCaseInput(
            BaseId.generate().toString(),
            "Customer Name",
            "(11) 98765-4321",
            LocalDate.now(),
            2
        );

        var reservation = createReservation();
        when(reservationRepository.create(any())).thenReturn(reservation);

        // Act
        var output = createUseCase.execute(input);

        // Assert
        assertThat(output).isNotNull();
        assertThat(output.customerName()).isEqualTo("Customer Name");
        verify(reservationRepository, times(1)).create(any());
    }

    @Test
    void shouldDeleteReservation() {
        // Arrange
        var reservationId = BaseId.generate().toString();
        var reservation = createReservation();
        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(reservation));
        doNothing().when(reservationRepository).delete(reservationId);

        // Act
        deleteUseCase.execute(reservationId);

        // Assert
        verify(reservationRepository, times(1)).delete(reservationId);
    }

    @Test
    void shouldThrowExceptionWhenReservationNotFoundForDelete() {
        // Arrange
        String reservationId = "non-existent-id";
        when(reservationRepository.findById(reservationId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> deleteUseCase.execute(reservationId))
            .isInstanceOf(CommonException.class)
            .hasMessage("Reservation with ID non-existent-id not found.");
    }

    @Test
    void shouldGetReservationById() {
        // Arrange
        String reservationId = BaseId.generate().toString();
        var reservation = createReservation();
        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(reservation));

        // Act
        var output = getByIdUseCase.execute(reservationId);

        // Assert
        assertThat(output).isNotNull();
        assertThat(output.customerName()).isEqualTo("Customer Name");
        verify(reservationRepository, times(1)).findById(reservationId);
    }

    @Test
    void shouldThrowExceptionWhenReservationNotFoundForGetById() {
        // Arrange
        String reservationId = "non-existent-id";
        when(reservationRepository.findById(reservationId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> getByIdUseCase.execute(reservationId))
            .isInstanceOf(CommonException.class)
            .hasMessage("Reservation with ID non-existent-id not found.");
    }

    @Test
    void shouldListReservations() {
        // Arrange
        var reservation = createReservation();
        when(reservationRepository.findAll()).thenReturn(List.of(reservation));

        // Act
        var outputs = listUseCase.execute();

        // Assert
        assertThat(outputs).isNotEmpty();
        assertThat(outputs.get(0).customerName()).isEqualTo("Customer Name");
        verify(reservationRepository, times(1)).findAll();
    }

    @Test
    void shouldUpdateReservationStatus() {
        // Arrange
        var reservationId = BaseId.generate().toString();
        var input = new ReservationUpdateUseCaseInput(reservationId, ReservationDTO.StatusEnum.CANCELED);
    
        var reservation = createReservation();
        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(reservation));
        when(reservationRepository.update(any())).thenReturn(reservation);
    
        // Act
        var output = updateUseCase.execute(input);
    
        // Assert
        assertThat(output).isNotNull();
        assertThat(output.status()).isEqualTo(ReservationDTO.StatusEnum.CANCELED);
        verify(reservationRepository, times(1)).findById(reservationId);
        verify(reservationRepository, times(1)).update(any());
    }

    @Test
    void shouldThrowExceptionWhenReservationNotFoundForUpdate() {
        // Arrange
        String reservationId = "non-existent-id";
        var input = new ReservationUpdateUseCaseInput(reservationId, ReservationDTO.StatusEnum.CANCELED);
        when(reservationRepository.findById(reservationId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> updateUseCase.execute(input))
            .isInstanceOf(CommonException.class)
            .hasMessage("Reservation with ID non-existent-id not found!");
    }
}


