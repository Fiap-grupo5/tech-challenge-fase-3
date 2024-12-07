package com.g5.restaurants.infrastructure.persistence.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.g5.restaurants.aplication.domain.reservation.Reservation;
import com.g5.restaurants.infrastructure.mappers.ReservationMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "reservation")
public class ReservationEntity {
    @Id
    private String id;

    private String restaurantId;

    private String customerName;

    private String customerContact;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate reservationDate;

    private Integer numberOfTables;

    private String status;

    public static ReservationEntity of(Reservation reservation) {
        return new ReservationEntity(
                reservation.getId().toString(),
                reservation.getRestaurantId().toString(),
                reservation.getCustomerName(),
                reservation.getCustomerContact(),
                reservation.getReservationDate(),
                reservation.getNumberOfTables(),
                reservation.getStatus().toString()
        );
    }

    public Reservation toReservation() {
        return ReservationMapper.INSTANCE.toObject( this);
    }
}
