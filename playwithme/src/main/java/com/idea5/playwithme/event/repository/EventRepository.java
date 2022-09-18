package com.idea5.playwithme.event.repository;

import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.event.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAll();
    List<Event> findByCategoryId(Integer id);
    List<Event> findAllByDateBetweenAndCategoryId(LocalDateTime start, LocalDateTime end, Integer categoryId);
    Page<Event> findByNameContainsOrLocationContains(String kw, String kw_,Pageable pageable);

}

