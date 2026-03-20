package com.example._9.midterm.project.service;
import com.example._9.midterm.project.dto.VenueDTO;
import com.example._9.midterm.project.entity.Venue;
import com.example._9.midterm.project.repository.VenueRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class VenueService {
    private final VenueRepository venueRepository;

    // CREATE VENUE
    @Transactional
    public VenueDTO createVenue(Venue venue){
        Venue saved = venueRepository.save(venue);
        return mapToDTO(saved);
    }

    private VenueDTO mapToDTO(Venue venue){
        return new VenueDTO(
                venue.getVenueId(),
                venue.getName(),
                venue.getAddress(),
                venue.getCity(),
                venue.getTotalCapacity()
        );
    }
}
