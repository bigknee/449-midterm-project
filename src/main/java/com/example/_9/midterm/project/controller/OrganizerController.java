package com.example._9.midterm.project.controller;

import com.example._9.midterm.project.dto.OrganizerDTO;
import com.example._9.midterm.project.entity.Organizer;
import com.example._9.midterm.project.service.OrganizerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organizers")
@RequiredArgsConstructor
public class OrganizerController {

    private final OrganizerService organizerService;

    @PostMapping
    public ResponseEntity<OrganizerDTO> createOrganizer(@RequestBody Organizer organizer) {
        OrganizerDTO created = organizerService.createOrganizer(organizer);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}