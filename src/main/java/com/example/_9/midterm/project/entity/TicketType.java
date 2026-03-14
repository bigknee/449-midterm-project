package com.example._9.midterm.project.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;


@Data
@Entity
@Table(name = "ticketType")
public class TicketType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticket_type_id;

    private String name;
    private BigDecimal price;
    private Integer quantity_available;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
}
