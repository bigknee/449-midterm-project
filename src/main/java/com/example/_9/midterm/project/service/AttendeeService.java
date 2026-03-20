package com.example._9.midterm.project.service;

import com.example._9.midterm.project.dto.AttendeeBookingsDTO;
import com.example._9.midterm.project.dto.AttendeeDTO;
import com.example._9.midterm.project.dto.BookingResponseDTO;
import com.example._9.midterm.project.entity.Attendee;
import com.example._9.midterm.project.entity.Booking;
import com.example._9.midterm.project.repository.AttendeeRepository;
import com.example._9.midterm.project.exception.BadRequestException;
import com.example._9.midterm.project.exception.ResourceNotFoundException;
import com.example._9.midterm.project.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendeeService {

    private final AttendeeRepository attendeeRepository;
    private final BookingRepository bookingRepository;

    // REGISTER NEW ATTENDEE
    @Transactional
    public AttendeeDTO registerAttendee(Attendee attendee) {
        // EMAIL MUST BE UNIQUE BEFORE CREATING
        if (attendeeRepository.existsByEmail(attendee.getEmail())){
            throw new BadRequestException("Email already exists");
        }

        Attendee saved = attendeeRepository.save(attendee);

        return mapToDTO(saved);
    }

    private AttendeeDTO mapToDTO(Attendee attendee){
        return new AttendeeDTO(
                attendee.getAttendeeId(),
                attendee.getName(),
                attendee.getEmail()
        );
    }

    public AttendeeBookingsDTO getAttendeeBookings(Long id) {

        //FINDING ATTENDEE
        Attendee attendee = attendeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendee not found."));

        //GET ALL BOOKING FOR ATTENDEE
        List<Booking> bookings = bookingRepository.findByAttendee(attendee);

        //MAP BOOKINGS TO DTOs
        List<BookingResponseDTO> bookingDTOs = bookings.stream()
                .map(booking -> {
                    BookingResponseDTO dto = new BookingResponseDTO();
                    dto.setBookingReference(booking.getBookingReference());
                    dto.setBookingDate(booking.getBookingDate());
                    dto.setPaymentStatus(booking.getPaymentStatus().name());
                    dto.setAttendeeName(booking.getAttendee().getName());
                    dto.setEventTitle(booking.getTicketType().getEvent().getTitle());
                    dto.setTicketTypename(booking.getTicketType().getName());
                    dto.setPrice(booking.getTicketType().getPrice());
                    return dto;
                })
                .collect(Collectors.toList());

        AttendeeBookingsDTO dto = new AttendeeBookingsDTO();
        dto.setAttendeeName(attendee.getName());
        dto.setBookingResponses(bookingDTOs);
        return dto;
    }
}
