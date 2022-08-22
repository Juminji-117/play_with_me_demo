package com.idea5.playwithme.event.domain.service;

import com.idea5.playwithme.event.domain.Event;
import com.idea5.playwithme.event.domain.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public List<Event> findByCategoryId(Integer id) {
        return eventRepository.findByCategoryId(id);

    }

    public List<Event> findByDate(LocalDateTime date) {
        return eventRepository.findByDate(date);

    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }



    public List<Event> getEventsByCategoryAndDate(Integer categoryId, LocalDate searchDate) {
        LocalDateTime start = LocalDateTime.of(searchDate, LocalTime.MIN);
        LocalDateTime end = LocalDateTime.of(searchDate, LocalTime.MAX);
        List<Event> events = eventRepository.findAllByDateBetweenAndCategoryId(start, end, categoryId);
        return events;
    }


/*
    void ObjectMapper__eventDtoMapToJsonStr() throws JsonProcessingException { // tojson ver.1
    ObjectMapper objectMapper = new ObjectMapper();
    eventDtos = getEventDto();
    for(EventDto eventDtos_ : eventDtos) {
        String eventDtoToJson = objectMapper.writeValueAsString(eventDtos_);
    }
    }
 */
    /*
public String eventDtoMapToJsonStr() { // tojson ver.2
    eventDtos = getEventDto();
    Map<Integer, EventDto> eventDtoMap = new HashMap<>();
    for (int i = 0; i<eventDtos.size(); i++){
        eventDtoMap.put(i, eventDtos.get(i));
    }
    String jsonStr = Ut.json.toStr(eventDtoMap, "");

    return jsonStr;
}
     */
}


