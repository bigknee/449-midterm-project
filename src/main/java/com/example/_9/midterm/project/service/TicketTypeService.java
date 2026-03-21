package com.example._9.midterm.project.controller;

import com.example._9.midterm.project.dto.TicketTypeDTO;
import com.example._9.midterm.project.service.TicketTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/ticket-types")
@RequiredArgsConstructor
public class TicketTypeController {

    private final TicketTypeService ticketTypeService;

    @PostMapping
    public ResponseEntity<TicketTypeDTO> createTicketType(
            @RequestParam Long eventId,
            @RequestParam String name,
            @RequestParam BigDecimal price,
            @RequestParam Integer quantityAvailable) {

        TicketTypeDTO created = ticketTypeService.createTicketType(
                eventId, name, price, quantityAvailable);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}