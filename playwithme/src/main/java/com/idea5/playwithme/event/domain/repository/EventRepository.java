package com.idea5.playwithme.event.domain.repository;

import com.idea5.playwithme.event.domain.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    public List<Event> findByCategoryId(Integer id);

    List<Event> findByDate(LocalDateTime date);
    Page<Event> findByCategoryIdAndTags(List<String> categoryIdList, List<String> filters, Pageable pageable);
}

