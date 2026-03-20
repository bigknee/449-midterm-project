package com.example._9.midterm.project.service;

import com.example._9.midterm.project.dto.EventResponseDTO;
import com.example._9.midterm.project.dto.TicketTypeDTO;
import com.example._9.midterm.project.entity.*;
import com.example._9.midterm.project.enums.EventStatus;
import com.example._9.midterm.project.repository.*;
import com.example._9.midterm.project.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final OrganizerRepository organizerRepository;
    private final VenueRepository venueRepository;
    private final TicketTypeRepository ticketTypeRepository;

    @Transactional
    public EventResponseDTO createEvent(String title, String description, LocalDateTime eventDate,
                                        EventStatus status,
                                        Long organizerId, Long venueId) {
        Organizer organizer = organizerRepository.findById(organizerId)
                .orElseThrow(() -> new ResourceNotFoundException("Organizer not found"));

        Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new ResourceNotFoundException("Venue not found"));

        Event event = new Event();
        event.setTitle(title);
        event.setDescription(description);
        event.setEventDate(eventDate);
        event.setStatus(status);
        event.setOrganizer(organizer);
        event.setVenue(venue);

        Event savedEvent = eventRepository.save(event);

        return mapToDTO(savedEvent);

    }

    private EventResponseDTO mapToDTO(Event event) {
        List<TicketType> ticketTypes = ticketTypeRepository.findByEvent(event);

        List<TicketTypeDTO> ticketTypeDTOS = ticketTypes.stream()
                .map(ticket -> {
                    TicketTypeDTO dto = new TicketTypeDTO();
                    dto.setTicketTypeId(ticket.getTicketTypeId());
                    dto.setName(ticket.getName());
                    dto.setPrice(ticket.getPrice());
                    dto.setQuantityAvailable(ticket.getQuantityAvailable());
                    return dto;
                })
                .collect(Collectors.toList());



        EventResponseDTO dto = new EventResponseDTO();
        dto.setEventId((event.getEventId()));
        dto.setTitle(event.getTitle());
        dto.setDescription(event.getDescription());
        dto.setEventDate(event.getEventDate());
        dto.setStatus(event.getStatus().name());
        dto.setOrganizerName(event.getOrganizer().getName());
        dto.setVenueName(event.getVenue().getName());
        dto.setTicketTypes(ticketTypeDTOS);

        return dto;
    }

    // GET ALL UPCOMING EVENTS
    public List<EventResponseDTO> getAllUpcomingEvents() {

        List<Event> events = eventRepository.findByStatus(EventStatus.UPCOMING);

        return events.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    // GET EVENT BY ID
    public EventResponseDTO getEventById(Long id) {

        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

        return mapToDTO(event);
    }
}