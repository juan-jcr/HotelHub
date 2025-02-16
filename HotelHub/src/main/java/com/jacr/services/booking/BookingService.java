package com.jacr.services.booking;

import com.jacr.persistence.entities.Booking;
import com.jacr.presentation.dto.Response;

public interface BookingService {
    Response saveBooking(Long roomId, Long userId, Booking bookingRequest);
    Response findBookingByConfirmationCode(String confirmationCode);

    Response getAllBookings();

    Response cancelBooking(Long bookingId);
}
