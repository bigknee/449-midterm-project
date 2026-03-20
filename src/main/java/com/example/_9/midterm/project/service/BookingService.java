package com.example._9.midterm.project.service;

import com.example._9.midterm.project.dto.BookingResponseDTO;
import com.example._9.midterm.project.dto.RevenueDTO;
import com.example._9.midterm.project.entity.*;
import com.example._9.midterm.project.enums.PaymentStatus;
import com.example._9.midterm.project.repository.*;
import com.example._9.midterm.project.exception.ResourceNotFoundException;
import com.example._9.midterm.project.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final AttendeeRepository attendeeRepository;
    private final TicketTypeRepository ticketTypeRepository;
    private final EventRepository eventRepository;

    @Transactional
    public BookingResponseDTO bookTicket(Long attendeeId, Long ticketTypeId) {

        //FIND ATTENDEE
        Attendee attendee = attendeeRepository.findById(attendeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Attendee not found."));

        //FIND TICKET TYPE
        TicketType ticketType = ticketTypeRepository.findById(ticketTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket type not found"));

        //CHECK AVAILABILITY
        if (ticketType.getQuantityAvailable() <= 0){
            throw new BadRequestException("Sorry! This ticket type is sold out");
        }

        //CHECK DUPLICATE BOOKING
        if (bookingRepository.findByAttendeeAndTicketType(attendee, ticketType).isPresent()) {
            throw new BadRequestException("You have already booked this ticket type.");
        }

        //DECREMENT QUANTITY
        ticketType.setQuantityAvailable(ticketType.getQuantityAvailable() - 1);
        ticketTypeRepository.save(ticketType);

        //CREATE BOOKING
        Booking booking = new Booking();
        booking.setAttendee(attendee);
        booking.setTicketType(ticketType);
        booking.setBookingDate(LocalDateTime.now());
        booking.setPaymentStatus(PaymentStatus.CONFIRMED);

        Booking saved = bookingRepository.save(booking);

        String reference = String.format("TKT-%d-%05d",
                LocalDateTime.now().getYear(),
                saved.getBookingId());
        saved.setBookingReference(reference);
        bookingRepository.save(saved);

        return mapToDTO(saved);
    }

    @Transactional
    public BookingResponseDTO cancelBooking(Long bookingId) {

        //FIND BOOKING
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        //CHECK IF IT WAS ALREADY CANCELLED
        if (booking.getPaymentStatus() == PaymentStatus.CANCELLED) {
            throw new BadRequestException("Booking is already cancelled.");
        }
        booking.setPaymentStatus(PaymentStatus.CANCELLED);

        TicketType ticketType = booking.getTicketType();
        ticketType.setQuantityAvailable(ticketType.getQuantityAvailable() + 1);
        ticketTypeRepository.save(ticketType);

        //SAVE BOOKING
        Booking saved = bookingRepository.save(booking);
        return mapToDTO(saved);
    }

    public RevenueDTO getRevenueForEvent(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

        //CALCULATING REVENUE
        BigDecimal revenue = bookingRepository.calculateRevenueByEventId(eventId);

        //IF NO CONFIRMED BOOKINGS EXIST
        if (revenue == null) {
            revenue = BigDecimal.ZERO;
        }

        RevenueDTO dto = new RevenueDTO();
        dto.setEventTitle(event.getTitle());
        dto.setTotalRevenue(revenue);
        return dto;
    }
    private BookingResponseDTO mapToDTO(Booking booking) {
        BookingResponseDTO dto = new BookingResponseDTO();
        dto.setBookingReference(booking.getBookingReference());
        dto.setBookingDate(booking.getBookingDate());
        dto.setPaymentStatus(booking.getPaymentStatus().name());
        dto.setAttendeeName(booking.getAttendee().getName());
        dto.setEventTitle(booking.getTicketType().getEvent().getTitle());
        dto.setTicketTypename(booking.getTicketType().getName());
        dto.setPrice(booking.getTicketType().getPrice());
        return dto;
    }
}

