package com.example._9.midterm.project.controller;

import com.example._9.midterm.project.dto.EventResponseDTO;
import com.example._9.midterm.project.dto.RevenueDTO;
import com.example._9.midterm.project.enums.EventStatus;
import com.example._9.midterm.project.service.EventService;
import com.example._9.midterm.project.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final BookingService bookingService;

    // POST /api/events
    @PostMapping
    public ResponseEntity<EventResponseDTO> createEvent(
            @RequestParam String title,
            @RequestParam(required = false) String description,
            @RequestParam LocalDateTime eventDate,
            @RequestParam EventStatus status,
            @RequestParam Long organizerId,
            @RequestParam Long venueId) {
        EventResponseDTO created = eventService.createEvent(
                title, description, eventDate, status, organizerId, venueId);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // GET /api/events
    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> getAllUpcomingEvents() {
        return ResponseEntity.ok(eventService.getAllUpcomingEvents());
    }

    // GET /api/events/{id}
    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEventById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    // GET /api/events/{id}/revenue
    @GetMapping("/{id}/revenue")
    public ResponseEntity<RevenueDTO> getRevenue(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getRevenueForEvent(id));
    }
}