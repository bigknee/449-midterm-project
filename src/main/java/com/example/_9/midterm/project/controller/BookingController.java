package com.example._9.midterm.project.controller;

import com.example._9.midterm.project.dto.BookingResponseDTO;
import com.example._9.midterm.project.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    // POST /api/bookings
    @PostMapping
    public ResponseEntity<BookingResponseDTO> bookTicket(
            @RequestParam Long attendeeId,
            @RequestParam Long ticketTypeId) {
        BookingResponseDTO created = bookingService.bookTicket(attendeeId, ticketTypeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT /api/bookings/{id}/cancel
    @PutMapping("/{id}/cancel")
    public ResponseEntity<BookingResponseDTO> cancelBooking(@PathVariable Long id) {
        BookingResponseDTO cancelled = bookingService.cancelBooking(id);
        return ResponseEntity.ok(cancelled);
    }
}