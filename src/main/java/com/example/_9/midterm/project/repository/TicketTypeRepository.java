package com.example._9.midterm.project.repository;

import com.example._9.midterm.project.entity.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example._9.midterm.project.repository.TicketTypeRepository;
import com.example._9.midterm.project.entity.Event;


import java.util.List;

@Repository
public interface TicketTypeRepository extends JpaRepository<TicketType, Long> {
    List<TicketType> findByEvent(Event event);
}