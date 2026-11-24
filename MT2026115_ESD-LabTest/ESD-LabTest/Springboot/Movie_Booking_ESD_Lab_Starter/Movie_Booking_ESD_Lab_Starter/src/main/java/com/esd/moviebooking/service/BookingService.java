package com.esd.moviebooking.service;

import com.esd.moviebooking.dto.BookingRequest;
import com.esd.moviebooking.dto.BookingResponse;
import com.esd.moviebooking.entity.Booking;
import com.esd.moviebooking.entity.BookingStatus;
import com.esd.moviebooking.entity.Customer;
import com.esd.moviebooking.entity.Movie;
import com.esd.moviebooking.exception.BookingAlreadyCancelledException;
import com.esd.moviebooking.exception.InsufficientSeatsException;
import com.esd.moviebooking.exception.ResourceNotFoundException;
import com.esd.moviebooking.repository.BookingRepository;
import com.esd.moviebooking.repository.CustomerRepository;
import com.esd.moviebooking.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final MovieRepository movieRepository;
    private final CustomerRepository customerRepository;

    /*
     * TODO: Implement the service logic for creating a new booking.
     *
     * Requirements:
     * - Verify the movie exists.
     * - Verify the customer exists.
     * - Create the booking.
     * - Set the initial status to CONFIRMED.
     * - Save the booking.
     * - Return the created booking.
     */
   public BookingResponse create(BookingRequest request){
       Movie movie = movieRepository.findById(request.getMovieId())
               .orElseThrow(()-> new ResourceNotFoundException("Movie not Found" + request.getMovieId()));

       Customer customer = customerRepository.findById(request.getCustomerId())
               .orElseThrow(()-> new ResourceNotFoundException("Movie not Found" + request.getMovieId()));

       if(request.getSeatsBooked()==null || request.getSeatsBooked()==0){
           throw new InsufficientSeatsException("Booking seat not provider");
       }
       Booking booking = new Booking();
       booking.setMovie(movie);
       booking.setCustomer(customer);
       booking.setBookingDate(LocalDateTime.now());
       booking.setSeatsBooked(request.getSeatsBooked());
       booking.setStatus(BookingStatus.CONFIRMED);

       Booking saved = bookingRepository.save(booking);
       return toResponse((saved));

   }
    public List<BookingResponse> getAll() {
        return bookingRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public BookingResponse getById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found: " + id));
        return toResponse(booking);
    }

    public BookingResponse cancel(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found: " + bookingId));

        if(booking.getStatus()==BookingStatus.CANCELLED){
            throw new BookingAlreadyCancelledException("Booking is already Cancelled " + bookingId);
        }

        booking.setStatus(BookingStatus.CANCELLED);
        Booking saved = bookingRepository.save(booking);
        return toResponse(saved);
    }

    private BookingResponse toResponse(Booking booking) {
        return new BookingResponse(
                booking.getId(),
                booking.getMovie().getId(),
                booking.getCustomer().getId(),
                booking.getBookingDate(),
                booking.getSeatsBooked(),
                booking.getStatus());
    }
}
