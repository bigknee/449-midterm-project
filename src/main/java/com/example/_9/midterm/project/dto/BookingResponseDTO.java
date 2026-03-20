package com.example._9.midterm.project.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.math.BigDecimal;

@Data
public class BookingResponseDTO {
    private String bookingReference;
    private LocalDateTime bookingDate;
    private String paymentStatus;
    private String attendeeName;
    private String eventTitle;
    private String ticketTypename;
    private BigDecimal price;

}