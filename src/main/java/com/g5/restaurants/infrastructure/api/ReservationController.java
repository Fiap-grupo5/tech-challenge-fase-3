package com.g5.restaurants.infrastructure.api;

import com.g5.reservation.api.ReservationApi;
import com.g5.reservation.model.CreateReservationDTO;
import com.g5.reservation.model.PaginateReservationDTO;
import com.g5.reservation.model.ReservationDTO;
import com.g5.reservation.model.UpdateReservationDTO;
import com.g5.restaurants.aplication.usecases.reservation.create.ReservationCreateUseCase;
import com.g5.restaurants.aplication.usecases.reservation.delete.ReservationDeleteUseCase;
import com.g5.restaurants.aplication.usecases.reservation.retrive.get.ReservationGetByIdUseCase;
import com.g5.restaurants.aplication.usecases.reservation.retrive.list.ReservationListUseCase;
import com.g5.restaurants.aplication.usecases.reservation.update.ReservationUpdateUseCase;
import com.g5.restaurants.infrastructure.mappers.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
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

    @Override
    public ResponseEntity<ReservationDTO> createReservation(CreateReservationDTO body) {
        final var useCaseInput = reservationMapper.fromDTO(body);
        final var useCaseOutput = reservationCreateUseCase.execute(useCaseInput);
        var uri = URI.create("/reservas/" + useCaseOutput.id());
        return ResponseEntity.created(uri).body(reservationMapper.toDTO(useCaseOutput));
    }

    @Override
    public ResponseEntity<PaginateReservationDTO> findReservation() {
        final var reservations = reservationListUseCase.execute().stream().map(reservationMapper::toDTO).collect(Collectors.toList());
        final var paginatedReservations = new PaginateReservationDTO();
        paginatedReservations.addAll(reservations);
        return ResponseEntity.ok(paginatedReservations);
    }

    @Override
    public ResponseEntity<ReservationDTO> findReservationById(String id) {
        final var useCaseOutput = reservationMapper.toDTO(reservationGetByIdUseCase.execute(id));
        return ResponseEntity.ok(useCaseOutput);
    }

    @Override
    public ResponseEntity<ReservationDTO> updateReservation(final String id, final UpdateReservationDTO body) {
        final var useCaseInput = reservationMapper.fromDTO(id, body);
        final var useCaseOutput = reservationMapper.toDTO(reservationUpdateUseCase.execute(useCaseInput));
        return ResponseEntity.ok(useCaseOutput);
    }

    @Override
    public ResponseEntity<Void> deleteReservation(String id) {
        reservationDeleteUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
