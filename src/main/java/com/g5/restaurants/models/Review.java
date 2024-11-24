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
@Document(collection = "review")
public class Review {
    @Id
    private String id;

    private String restaurantId;

    private String reviewerName;

    private Double rating;

    private String comments;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime reviewDate;

    @Version
    private Long version;
}
