package com.example._9.midterm.project.controller;

import com.example._9.midterm.project.dto.AttendeeBookingsDTO;
import com.example._9.midterm.project.dto.AttendeeDTO;
import com.example._9.midterm.project.entity.Attendee;
import com.example._9.midterm.project.service.AttendeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attendees")
@RequiredArgsConstructor
public class AttendeeController {

    private final AttendeeService attendeeService;

    // POST /api/attendees
    @PostMapping
    public ResponseEntity<AttendeeDTO> registerAttendee(@RequestBody Attendee attendee) {
        AttendeeDTO created = attendeeService.registerAttendee(attendee);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // GET /api/attendees/{id}/bookings
    @GetMapping("/{id}/bookings")
    public ResponseEntity<AttendeeBookingsDTO> getAttendeeBookings(@PathVariable Long id) {
        return ResponseEntity.ok(attendeeService.getAttendeeBookings(id));
    }
}