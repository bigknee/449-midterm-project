package com.example._9.midterm.project.repository;

import com.example._9.midterm.project.entity.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee, Long> {
    boolean existsByEmail(String email);
    Optional<Attendee> findByEmail(String email);
}