package com.jacr.persistence.repositories;

import com.jacr.persistence.entities.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {
    Optional<Booking> findByBookingConfirmationCode(String confirmationCode);
}
