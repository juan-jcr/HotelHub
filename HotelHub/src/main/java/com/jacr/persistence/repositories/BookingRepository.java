package com.jacr.persistence.repositories;

import com.jacr.persistence.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findByBookingConfirmationCode(String confirmationCode);
}
