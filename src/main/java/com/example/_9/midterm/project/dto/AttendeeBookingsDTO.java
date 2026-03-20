package com.example._9.midterm.project.dto;

import lombok.Data;
import java.util.List;

@Data
public class AttendeeBookingsDTO {
    private String attendeeName;
    private List<BookingResponseDTO> bookingResponses;
}
