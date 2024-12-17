package com.g5.restaurants.aplication.domain.reservation;

import com.g5.reservation.model.ReservationDTO;
import com.g5.restaurants.aplication.domain.base.BaseId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

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

    public static Reservation newReservation(
            BaseId restaurantId,
            String customerName,
            String customerContact,
            LocalDate reservationDate,
            Integer numberOfTables,
            ReservationDTO.StatusEnum status
    ) {
        if (customerName == null || customerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be null or empty");
        }

        if (customerContact == null || !isValidContact(customerContact)) {
            throw new IllegalArgumentException("Invalid contact format. Expected formats: (DDD) 00000-0000 or (DDD) 0000-0000");
        }

        if (numberOfTables == null || numberOfTables < 1) {
            throw new IllegalArgumentException("Number of tables must be at least 1");
        }

        var id = BaseId.generate();
        return new Reservation(
                id,
                restaurantId,
                customerName,
                customerContact,
                reservationDate,
                numberOfTables,
                status
        );
    }

    public void update(
            String customerName,
            String customerContact,
            LocalDate reservationDate,
            Integer numberOfTables
    ) {
        if (customerName != null && !customerName.trim().isEmpty()) {
            this.customerName = customerName;
        } else if (customerName != null) {
            throw new IllegalArgumentException("Customer name cannot be empty");
        }

        if (customerContact != null && isValidContact(customerContact)) {
            this.customerContact = customerContact;
        } else if (customerContact != null) {
            throw new IllegalArgumentException("Invalid contact format. Expected formats: (DDD) 00000-0000 or (DDD) 0000-0000");
        }

        if (numberOfTables != null && numberOfTables >= 1) {
            this.numberOfTables = numberOfTables;
        } else if (numberOfTables != null) {
            throw new IllegalArgumentException("Number of tables must be at least 1");
        }

        if (reservationDate != null) {
            this.reservationDate = reservationDate;
        }
    }

    public void update(ReservationDTO.StatusEnum status) {
        if (status != null) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Status cannot be null");
        }
    }

    private static boolean isValidContact(String contact) {
        String regex = "^\\(\\d{2}\\) (\\d{4,5}-\\d{4})$";
        return contact.matches(regex);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
