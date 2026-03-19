package com.example._9.midterm.project.repository;

import com.example._9.midterm.project.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Long>{
}
