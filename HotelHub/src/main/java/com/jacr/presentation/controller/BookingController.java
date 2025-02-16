package com.jacr.presentation.controller;

import com.jacr.persistence.entities.Booking;
import com.jacr.presentation.dto.Response;
import com.jacr.services.booking.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/book-room/{roomId}/{userId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Response> saveBookings(@PathVariable Long roomId,
                                                 @PathVariable Long userId,
                                                 @RequestBody @Valid Booking bookingRequest) {
        return new ResponseEntity<>(bookingService.saveBooking(roomId, userId, bookingRequest), HttpStatus.CREATED);

    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> getAllBookings() {
        return new ResponseEntity<>(bookingService.getAllBookings(), HttpStatus.OK);

    }

    @GetMapping("/get-by-confirmation-code/{confirmationCode}")
    public ResponseEntity<Response> getBookingByConfirmationCode(@PathVariable String confirmationCode) {
        return new ResponseEntity<>(bookingService.findBookingByConfirmationCode(confirmationCode), HttpStatus.OK);
    }

    @DeleteMapping("/cancel/{bookingId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Response> cancelBooking(@PathVariable Long bookingId) {
        return new ResponseEntity<>(bookingService.cancelBooking(bookingId), HttpStatus.OK);
    }

}
