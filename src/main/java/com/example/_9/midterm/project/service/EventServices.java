package com.example._9.midterm.project.service;

import com.example._9.midterm.project.dto.EventResponseDTO;
import com.example._9.midterm.project.dto.TicketTypeDTO;
import com.example._9.midterm.project.entity.*;
import com.example._9.midterm.project.repository.*;
import com.example._9.midterm.project.ticketing.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final OrganizerRepository organizerRepository;
    private final VenueRepository venueRepository;

    // CREATE EVENT
    public EventResponseDTO createEvent(Event event) {

        // VALIDATION (REQUIRED BY SPEC)
        Organizer organizer = organizerRepository.findById(event.getOrganizer().getOrganizerId())
                .orElseThrow(() -> new ResourceNotFoundException("Organizer not found"));

        Venue venue = venueRepository.findById(event.getVenue().getVenueId())
                .orElseThrow(() -> new ResourceNotFoundException("Venue not found"));

        event.setOrganizer(organizer);
        event.setVenue(venue);

        Event savedEvent = eventRepository.save(event);

        return mapToDTO(savedEvent);
    }

    // GET ALL UPCOMING EVENTS
    public List<EventResponseDTO> getAllUpcomingEvents() {

        List<Event> events = eventRepository.findByStatus(EventStatus.UPCOMING);

        return events.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // GET EVENT BY ID (WITH TICKET TYPES)
    public EventResponseDTO getEventById(Long id) {

        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

        return mapToDTO(event);
    }

    // ENTITY → DTO MAPPING (VERY IMPORTANT)
    private EventResponseDTO mapToDTO(Event event) {

        List<TicketTypeDTO> ticketDTOs = event.getTicketTypes()
                .stream()
                .map(ticket -> new TicketTypeDTO(
                        ticket.getTicketTypeId(),
                        ticket.getName(),
                        ticket.getPrice(),
                        ticket.getQuantityAvailable()
                ))
                .collect(Collectors.toList());

        return new EventResponseDTO(
                event.getEventId(),
                event.getTitle(),
                event.getDescription(),
                event.getEventDate(),
                event.getStatus(),
                event.getOrganizer().getName(),
                event.getVenue().getName(),
                ticketDTOs
        );
    }
}