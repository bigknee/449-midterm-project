package com.example._9.midterm.project.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class EventResponseDTO {
    private Long eventId;
    private String title;
    private String description;
    private LocalDateTime eventDate;
    private String status;
    private String organizerName;
    private String venueName;
    private List<TicketTypeDTO> ticketTypes;
}
