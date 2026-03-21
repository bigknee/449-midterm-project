package com.example._9.midterm.project.service;

import com.example._9.midterm.project.dto.TicketTypeDTO;
import com.example._9.midterm.project.entity.Event;
import com.example._9.midterm.project.entity.TicketType;
import com.example._9.midterm.project.exception.ResourceNotFoundException;
import com.example._9.midterm.project.repository.EventRepository;
import com.example._9.midterm.project.repository.TicketTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TicketTypeService {

    private final TicketTypeRepository ticketTypeRepository;
    private final EventRepository eventRepository;

    @Transactional
    public TicketTypeDTO createTicketType(Long eventId, String name,
                                          BigDecimal price,
                                          Integer quantityAvailable) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

        TicketType tt = new TicketType();
        tt.setEvent(event);
        tt.setName(name);
        tt.setPrice(price);
        tt.setQuantityAvailable(quantityAvailable);

        TicketType saved = ticketTypeRepository.save(tt);

        TicketTypeDTO dto = new TicketTypeDTO();
        dto.setTicketTypeId(saved.getTicketTypeId());
        dto.setName(saved.getName());
        dto.setPrice(saved.getPrice());
        dto.setQuantityAvailable(saved.getQuantityAvailable());
        return dto;
    }
}