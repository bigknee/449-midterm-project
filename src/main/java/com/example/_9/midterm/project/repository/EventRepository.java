package com.example._9.midterm.project.repository;

import com.example._9.midterm.project.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example._9.midterm.project.enums.EventStatus;

import java.util.List;
@Repository
public interface EventRepository extends JpaRepository<Event, Long>{
    List<Event> findByStatus(EventStatus status);
}
