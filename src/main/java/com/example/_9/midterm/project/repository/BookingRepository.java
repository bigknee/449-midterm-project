package com.example._9.midterm.project.repository;

import com.example._9.midterm.project.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Get all bookings for an attendee
    List<Booking> findByAttendee(Attendee attendee);

    // Check if attendee already booked same ticket type
    Optional<Booking> findByAttendeeAndTicketType(Attendee attendee, TicketType ticketType);

    // Calculate total confirmed revenue for an event
    @Query("SELECT SUM(t.price) FROM Booking b JOIN b.ticketType t " +
            "WHERE t.event.eventId = :eventId AND b.paymentStatus = 'CONFIRMED'")
    BigDecimal calculateRevenueByEventId(@Param("eventId") Long eventId);
}