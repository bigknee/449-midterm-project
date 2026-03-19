package com.example._9.midterm.project.dto;

import lombok.Data;
import java.util.List;

@Data
public class AttendeeBookingsDTO {
    private String attendee;
    private List<BookingResponseDTO> booking_responses;
}
