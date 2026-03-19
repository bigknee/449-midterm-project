package com.example._9.midterm.project.dto;

import com.example._9.midterm.project.entity.Attendee;
import com.example._9.midterm.project.entity.TicketType;
import com.example._9.midterm.project.enums.PaymentStatus;
import jakarta.persistence.*;

import lombok.Data;
import java.time.LocalDateTime;
@Data
public class BookingResponseDTO {
    private String bookingReference;
    private Long bookingDate;
    private String paymentStatus;
    private String attendeeName;
    private String ticketTypename;
    private Long price;

}