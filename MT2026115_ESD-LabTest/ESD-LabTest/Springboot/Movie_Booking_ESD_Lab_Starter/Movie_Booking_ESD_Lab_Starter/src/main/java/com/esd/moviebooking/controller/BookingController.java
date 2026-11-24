package com.esd.moviebooking.controller;

import com.esd.moviebooking.dto.BookingRequest;
import com.esd.moviebooking.dto.BookingResponse;
import com.esd.moviebooking.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    /*
     * TODO: Implement the API for creating a new booking.
     *
     * Requirements:
     * - Create the required request DTO.
     * - Accept the request body.
     * - Apply request validation.
     * - Call the service layer.
     * - Verify that the referenced movie and customer exist.untitled
     * - Create and save the booking with CONFIRMED status.
     * - Return the created booking.
     * - Return the appropriate HTTP status.
     */
    @PostMapping()
    public ResponseEntity <BookingResponse> createBooking(@Valid @RequestBody BookingRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookingService.create(request));
    }


    @GetMapping
    public ResponseEntity<List<BookingResponse>> getAll() {
        return ResponseEntity.ok(bookingService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getById(id));
    }

    @PutMapping("/{bookingId}/cancel")
    public ResponseEntity<BookingResponse> cancel(@PathVariable Long bookingId) {
        return ResponseEntity.ok(bookingService.cancel(bookingId));
    }
}
