package com.g5.restaurants.infrastructure.api;

import com.g5.reservation.api.ReservationApi;
import com.g5.reservation.model.CreateReservationDTO;
import com.g5.reservation.model.PaginateReservationDTO;
import com.g5.reservation.model.ReservationDTO;
import com.g5.reservation.model.UpdateReservationDTO;
import com.g5.restaurants.aplication.exceptions.CommonException;
import com.g5.restaurants.aplication.usecases.reservation.create.ReservationCreateUseCase;
import com.g5.restaurants.aplication.usecases.reservation.delete.ReservationDeleteUseCase;
import com.g5.restaurants.aplication.usecases.reservation.retrive.get.ReservationGetByIdUseCase;
import com.g5.restaurants.aplication.usecases.reservation.retrive.list.ReservationListUseCase;
import com.g5.restaurants.aplication.usecases.reservation.update.ReservationUpdateUseCase;
import com.g5.restaurants.infrastructure.mappers.ReservationMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ReservationController implements ReservationApi {
    private static final ReservationMapper reservationMapper = ReservationMapper.INSTANCE;

    private final ReservationCreateUseCase reservationCreateUseCase;
    private final ReservationDeleteUseCase reservationDeleteUseCase;
    private final ReservationListUseCase reservationListUseCase;
    private final ReservationGetByIdUseCase reservationGetByIdUseCase;
    private final ReservationUpdateUseCase reservationUpdateUseCase;

    // Tratamento de exceções customizadas
    @ExceptionHandler(CommonException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleCommonException(CommonException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", ex.getMessage()));
    }

    // Tratamento de erro para UUID inválido
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
    }

    // Tratamento de erros gerais
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("message", "Internal Server Error", "error", ex.getMessage()));
    }

    // Criação de reserva
    @Override
    public ResponseEntity<ReservationDTO> createReservation(CreateReservationDTO body) {
        final var useCaseInput = reservationMapper.fromDTO(body);
        final var useCaseOutput = reservationCreateUseCase.execute(useCaseInput);
        var uri = URI.create("/reservas/" + useCaseOutput.id());
        return ResponseEntity.created(uri).body(reservationMapper.toDTO(useCaseOutput));
    }

    // Listagem de reservas
    @Override
    public ResponseEntity<PaginateReservationDTO> findReservation() {
        final var reservations = reservationListUseCase.execute()
                .stream()
                .map(reservationMapper::toDTO)
                .collect(Collectors.toList());

        final var paginatedReservations = new PaginateReservationDTO();
        paginatedReservations.addAll(reservations);

        return ResponseEntity.ok(paginatedReservations);
    }

    // Busca de reserva por ID com validação de UUID
    @Override
    public ResponseEntity<ReservationDTO> findReservationById(String id) {
        try {
            UUID.fromString(id); // Validação do UUID
            final var useCaseOutput = reservationMapper.toDTO(reservationGetByIdUseCase.execute(id));
            return ResponseEntity.ok(useCaseOutput);
        } catch (IllegalArgumentException e) {
            throw new CommonException("Invalid reservation ID format.", HttpStatus.BAD_REQUEST);
        }
    }

    // Atualização de reserva
    @Override
    public ResponseEntity<ReservationDTO> updateReservation(final String id, final UpdateReservationDTO body) {
        try {
            UUID.fromString(id); // Validação do UUID
            final var useCaseInput = reservationMapper.fromDTO(id, body);
            final var useCaseOutput = reservationMapper.toDTO(reservationUpdateUseCase.execute(useCaseInput));
            return ResponseEntity.ok(useCaseOutput);
        } catch (IllegalArgumentException e) {
            throw new CommonException("Invalid reservation ID format.", HttpStatus.BAD_REQUEST);
        }
    }

    // Exclusão de reserva
    @Override
    public ResponseEntity<Void> deleteReservation(String id) {
        try {
            UUID.fromString(id); // Validação do UUID
            reservationDeleteUseCase.execute(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            throw new CommonException("Invalid reservation ID format.", HttpStatus.BAD_REQUEST);
        }
    }
}
