package com.example._9.midterm.project.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class AttendeeDTO {
    private Long attendeeId;
    private String name;
    private String email;
}
