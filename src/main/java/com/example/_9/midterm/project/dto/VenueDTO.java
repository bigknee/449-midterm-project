package com.example._9.midterm.project.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VenueDTO {
    private Long venueId;
    private String name;
    private String address;
    private String city;
    private Integer totalCapacity;
}
