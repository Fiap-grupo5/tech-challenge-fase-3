package com.g5.restaurants.aplication.domain.reservation;

import com.g5.reservation.model.ReservationDTO;
import com.g5.restaurants.aplication.domain.base.BaseId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    private BaseId id;
    private BaseId restaurantId;
    private String customerName;
    private String customerContact;
    private LocalDate reservationDate;
    private Integer numberOfTables;
    private ReservationDTO.StatusEnum status;

    public static Reservation newReservation(BaseId restaurantId, String customerName, String customerContact, LocalDate reservationDate, Integer numberOfTables, ReservationDTO.StatusEnum status) {
        var id = BaseId.generate();
        return new Reservation(id, restaurantId, customerName, customerContact, reservationDate, numberOfTables, status);
    }

    public void update(ReservationDTO.StatusEnum status) {
        this.status = status;
    }
}
