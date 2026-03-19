package com.example._9.midterm.project.ticketing.service;

import com.yourgroup.ticketing.dto.OrganizerDTO;
import com.yourgroup.ticketing.entity.Organizer;
import com.yourgroup.ticketing.repository.OrganizerRepository;
import com.yourgroup.ticketing.exception.BadRequestException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizerService {

    private final OrganizerRepository organizerRepository;

    // ✅ CREATE ORGANIZER
    public OrganizerDTO createOrganizer(Organizer organizer) {

        // 🔥 VALIDATION: email must be unique
        if (organizerRepository.existsByEmail(organizer.getEmail())) {
            throw new BadRequestException("Email already exists.");
        }

        Organizer saved = organizerRepository.save(organizer);

        return mapToDTO(saved);
    }

    // 🔁 ENTITY → DTO
    private OrganizerDTO mapToDTO(Organizer organizer) {
        return new OrganizerDTO(
                organizer.getOrganizerId(),
                organizer.getName(),
                organizer.getEmail(),
                organizer.getPhone()
        );
    }
}