package com.g5.restaurants.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Jacksonized
@Document(collection = "reservation")
public class Reservation {
    @Id
    private String id;

    private String restaurantId;

    private String customerName;

    private String customerContact;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime reservationDateTime;

    private Integer numberOfTables;

    private ReservationStatus status;

    @Version
    private Long version;
}
