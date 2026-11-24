package com.esd.moviebooking.dto;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {
    @NonNull
    @Positive
    private Long movieId;

    @NonNull
    @Positive
    private Long customerId;

    @NonNull
    @Positive
    private Integer seatsBooked;
}
