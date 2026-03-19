package com.example._9.midterm.project.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data //generates getters and setters
public class TicketTypeDTO {
    private Long ticketTypeId;
    private String name;
    private BigDecimal price;
    private Integer quantityAvailable;
}
