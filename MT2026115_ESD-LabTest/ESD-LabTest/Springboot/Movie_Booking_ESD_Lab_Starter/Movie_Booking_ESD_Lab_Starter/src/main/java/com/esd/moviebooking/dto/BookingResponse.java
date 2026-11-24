package com.esd.moviebooking.dto;

import com.esd.moviebooking.entity.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponse {
    private Long id;
    private Long movieId;
    private Long customerId;
    private LocalDateTime bookingDate;
    private Integer seatsBooked;
    private BookingStatus status;
}
