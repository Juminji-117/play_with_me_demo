package com.idea5.playwithme.event.domain.repository;

import com.idea5.playwithme.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByCategoryId(Long id);

}
