package com.example._9.midterm.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class OrganizerDTO {
    private Long organizerId;
    private String name;
    private String email;
    private String phone;
}
