package com.example._9.midterm.project.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "attendee")

public class Attendee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendeeId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;
}
