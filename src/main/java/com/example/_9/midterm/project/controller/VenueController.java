package com.example._9.midterm.project.controller;

import com.example._9.midterm.project.dto.VenueDTO;
import com.example._9.midterm.project.entity.Venue;
import com.example._9.midterm.project.service.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/venues")
@RequiredArgsConstructor
public class VenueController {

    private final VenueService venueService;

    @PostMapping
    public ResponseEntity<VenueDTO> createVenue(@RequestBody Venue venue) {
        VenueDTO created = venueService.createVenue(venue);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}