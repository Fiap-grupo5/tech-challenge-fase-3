package com.g5.restaurants.aplication.infrastructure.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.g5.reservation.model.*;
import com.g5.restaurants.aplication.domain.base.BaseId;
import com.g5.restaurants.aplication.domain.reservation.Reservation;
import com.g5.restaurants.aplication.usecases.reservation.create.ReservationCreateUseCase;
import com.g5.restaurants.aplication.usecases.reservation.create.ReservationCreateUseCaseInput;
import com.g5.restaurants.aplication.usecases.reservation.delete.ReservationDeleteUseCase;
import com.g5.restaurants.aplication.usecases.reservation.retrive.get.ReservationGetByIdUseCase;
import com.g5.restaurants.aplication.usecases.reservation.retrive.get.ReservationGetByIdUseCaseOutput;
import com.g5.restaurants.aplication.usecases.reservation.retrive.list.ReservationListUseCase;
import com.g5.restaurants.aplication.usecases.reservation.retrive.list.ReservationListUseCaseOutput;
import com.g5.restaurants.aplication.usecases.reservation.update.ReservationUpdateUseCase;
import com.g5.restaurants.aplication.usecases.reservation.update.ReservationUpdateUseCaseInput;
import com.g5.restaurants.aplication.usecases.reservation.update.ReservationUpdateUseCaseOutput;
import com.g5.restaurants.infrastructure.api.ReservationController;
import com.g5.restaurants.infrastructure.mappers.ReservationMapper;
import com.g5.restaurants.aplication.usecases.reservation.create.ReservationCreateUseCaseOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

class ReservationControllerTest {

    @InjectMocks
    private ReservationController reservationController;

    @Mock
    private ReservationCreateUseCase reservationCreateUseCase;

    @Mock
    private ReservationDeleteUseCase reservationDeleteUseCase;

    @Mock
    private ReservationListUseCase reservationListUseCase;

    @Mock
    private ReservationGetByIdUseCase reservationGetByIdUseCase;

    @Mock
    private ReservationUpdateUseCase reservationUpdateUseCase;

    @Mock
    private ReservationMapper reservationMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

        @Test
        void shouldCreateReservation() {
        // Arrange
        CreateReservationDTO createDTO = new CreateReservationDTO()
                .customerName("John Doe")
                .customerContact("(11) 98765-4321")
                .reservationDate(LocalDate.now())
                .numberOfTables(2)
                .restaurantId(UUID.randomUUID());

        var reservationInput = new ReservationCreateUseCaseInput(
                createDTO.getRestaurantId().toString(),
                createDTO.getCustomerName(),
                createDTO.getCustomerContact(),
                createDTO.getReservationDate(),
                createDTO.getNumberOfTables()
        );

        var useCaseOutput = ReservationCreateUseCaseOutput.from(
                new Reservation(
                        BaseId.generate(),
                        BaseId.generate(),
                        "John Doe",
                        "(11) 98765-4321",
                        LocalDate.now(),
                        2,
                        ReservationDTO.StatusEnum.PENDING
                ));

        ReservationDTO responseDTO = new ReservationDTO()
                .id(UUID.fromString(useCaseOutput.id().toString()))
                .restaurantId(UUID.fromString(useCaseOutput.restaurantId().toString()))
                .customerName(useCaseOutput.customerName())
                .customerContact(useCaseOutput.customerContact())
                .reservationDate(useCaseOutput.reservationDate())
                .numberOfTables(useCaseOutput.numberOfTables())
                .status(ReservationDTO.StatusEnum.PENDING);
        

        when(reservationMapper.fromDTO(createDTO)).thenReturn(reservationInput);
        when(reservationCreateUseCase.execute(any())).thenReturn(useCaseOutput);
        when(reservationMapper.toDTO(useCaseOutput)).thenReturn(responseDTO);

        // Act
        ResponseEntity<ReservationDTO> response = reservationController.createReservation(createDTO);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(responseDTO);
        assertThat(response.getHeaders().getLocation()).isEqualTo(URI.create("/reservas/" + useCaseOutput.id()));
        verify(reservationCreateUseCase, times(1)).execute(any());
        }

        @Test
        void shouldFindAllReservations() {
                // Arrange
                var reservationList = List.of(
                        new ReservationListUseCaseOutput(
                        BaseId.generate(),
                        BaseId.generate(),
                        "John Doe",
                        "(11) 98765-4321",
                        LocalDate.now(),
                        2,
                        ReservationDTO.StatusEnum.CONFIRMED
                        ),
                        new ReservationListUseCaseOutput(
                        BaseId.generate(),
                        BaseId.generate(),
                        "Jane Doe",
                        "(11) 91234-5678",
                        LocalDate.now(),
                        3,
                        ReservationDTO.StatusEnum.PENDING
                        )
                );

                PaginateReservationDTO paginatedDTO = new PaginateReservationDTO();
                paginatedDTO.addAll(reservationList.stream().map(output -> new ReservationDTO()
                        .id(UUID.randomUUID())
                        .restaurantId(UUID.randomUUID())
                        .customerName(output.customerName())
                        .customerContact(output.customerContact())
                        .reservationDate(output.reservationDate())
                        .numberOfTables(output.numberOfTables())
                        .status(output.status())).toList());

                when(reservationListUseCase.execute()).thenReturn(reservationList);

                // Act
                ResponseEntity<PaginateReservationDTO> response = reservationController.findReservation();

                // Assert
                assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
                assertThat(response.getBody()).isNotNull();
                verify(reservationListUseCase, times(1)).execute();
        }

        @Test
        void shouldFindReservationById() {
                // Arrange
                var id = UUID.randomUUID().toString();
                var restaurantId = UUID.randomUUID().toString();
                
                var useCaseOutput = new ReservationGetByIdUseCaseOutput(
                        new BaseId(id),
                        new BaseId(restaurantId),
                        "John Doe",
                        "(11) 98765-4321",
                        LocalDate.now(),
                        2,
                        ReservationDTO.StatusEnum.PENDING
                );
                
                ReservationDTO responseDTO = new ReservationDTO()
                        .id(UUID.fromString(id))
                        .restaurantId(UUID.fromString(restaurantId))
                        .customerName(useCaseOutput.customerName())
                        .customerContact(useCaseOutput.customerContact())
                        .reservationDate(useCaseOutput.reservationDate())
                        .numberOfTables(useCaseOutput.numberOfTables())
                        .status(useCaseOutput.status());
        
                when(reservationGetByIdUseCase.execute(id)).thenReturn(useCaseOutput);
                when(reservationMapper.toDTO(useCaseOutput)).thenReturn(responseDTO);
        
                // Act
                ResponseEntity<ReservationDTO> response = reservationController.findReservationById(id);
        
                // Assert
                assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
                assertThat(response.getBody()).isEqualTo(responseDTO);
                verify(reservationGetByIdUseCase, times(1)).execute(id);
        }

        @Test
        void shouldUpdateReservation() {
                // Arrange
                var id = UUID.randomUUID().toString();
                UpdateReservationDTO updateDTO = new UpdateReservationDTO().status(UpdateReservationDTO.StatusEnum.CONFIRMED);

                var reservationInput = new ReservationUpdateUseCaseInput(
                        id,
                        ReservationDTO.StatusEnum.valueOf(updateDTO.getStatus().name())
                );

                var useCaseOutput = new ReservationUpdateUseCaseOutput(
                        new BaseId(id),
                        new BaseId(UUID.randomUUID().toString()),
                        "John Doe",
                        "(11) 98765-4321",
                        LocalDate.now(),
                        2,
                        ReservationDTO.StatusEnum.CONFIRMED
                );

                ReservationDTO responseDTO = new ReservationDTO()
                        .id(UUID.fromString(useCaseOutput.id().toString()))
                        .restaurantId(UUID.fromString(useCaseOutput.restaurantId().toString()))
                        .customerName(useCaseOutput.customerName())
                        .customerContact(useCaseOutput.customerContact())
                        .reservationDate(useCaseOutput.reservationDate())
                        .numberOfTables(useCaseOutput.numberOfTables())
                        .status(useCaseOutput.status());

                when(reservationMapper.fromDTO(eq(id), eq(updateDTO))).thenReturn(reservationInput);
                when(reservationUpdateUseCase.execute(any())).thenReturn(useCaseOutput);
                when(reservationMapper.toDTO(useCaseOutput)).thenReturn(responseDTO);

                // Act
                ResponseEntity<ReservationDTO> response = reservationController.updateReservation(id, updateDTO);

                // Assert
                assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
                assertThat(response.getBody()).isEqualTo(responseDTO);
                verify(reservationUpdateUseCase, times(1)).execute(any());
        }


        @Test
        void shouldDeleteReservation() {
                // Arrange
                var id = UUID.randomUUID().toString();
                doNothing().when(reservationDeleteUseCase).execute(id);

                // Act
                ResponseEntity<Void> response = reservationController.deleteReservation(id);

                // Assert
                assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
                verify(reservationDeleteUseCase, times(1)).execute(id);
        }
}
