package com.idea5.playwithme.event.domain.service;

import com.idea5.playwithme.event.domain.Event;
import com.idea5.playwithme.event.domain.dto.EventDto;
import com.idea5.playwithme.event.domain.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;

    public List<Event> findByCategoryId(Integer id) {
        return eventRepository.findByCategoryId(id);

    }

    public List<Event> findByDate(LocalDateTime date) {
        return eventRepository.findByDate(date);

    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Transactional
    public List<EventDto> getEventDto() {
        return eventRepository.findAll().stream()
                .map(content -> modelMapper.map(content, EventDto.class)).collect(Collectors.toList());
    }
}


