package com.example._9.midterm.project.service;

import com.example._9.midterm.project.dto.OrganizerDTO;
import com.example._9.midterm.project.entity.Organizer;
import com.example._9.midterm.project.repository.OrganizerRepository;
import com.example._9.midterm.project.exception.BadRequestException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class OrganizerService {

    private final OrganizerRepository organizerRepository;

    // CREATE ORGANIZER
    @Transactional
    public OrganizerDTO createOrganizer(Organizer organizer) {

        // EMAIL MUST BE UNIQUE BEFORE CREATING
        if (organizerRepository.existsByEmail(organizer.getEmail())) {
            throw new BadRequestException("Email already exists.");
        }

        Organizer saved = organizerRepository.save(organizer);

        return mapToDTO(saved);
    }

    // CONVERT TO DTO
    private OrganizerDTO mapToDTO(Organizer organizer) {
        return new OrganizerDTO(
                organizer.getOrganizerId(),
                organizer.getName(),
                organizer.getEmail(),
                organizer.getPhone()
        );
    }
}