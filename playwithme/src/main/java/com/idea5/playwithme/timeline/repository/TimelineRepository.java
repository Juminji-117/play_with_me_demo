package com.idea5.playwithme.timeline.repository;

import com.idea5.playwithme.timeline.domain.Timeline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimelineRepository extends JpaRepository<Timeline, Long> {
}
