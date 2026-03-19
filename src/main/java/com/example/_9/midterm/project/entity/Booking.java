package com.example._9.midterm.project.entity;

import com.example._9.midterm.project.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long booking_id;

    @Column(unique = true)
    private String booking_reference;

    @Column(nullable = false)
    private LocalDateTime booking_date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus payment_status;

    @ManyToOne
    @JoinColumn(name = "attendee_id", nullable = false)
    private Attendee attendee;

    @ManyToOne
    @JoinColumn(name = "ticket_type_id", nullable = false)
    private TicketType ticketType;
}
