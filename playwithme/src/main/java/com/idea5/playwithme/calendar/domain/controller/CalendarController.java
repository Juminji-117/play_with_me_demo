package com.idea5.playwithme.calendar.domain.controller;

import com.idea5.playwithme.event.domain.Event;
import com.idea5.playwithme.event.domain.service.EventService;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Controller
public class CalendarController {
    private EventService eventService;
    private List<Event> events = new ArrayList<>();

    /*
    @GetMapping("/event") // 템플릿 적용 확인 테스트용
    public String showCalendar() {
        return "calendar";
    }
*/

    @GetMapping("/event")
    public String showEvent(Model model, @RequestParam String category,@RequestParam(defaultValue = "new SimpleDateFormat(\"yyyy-MM-dd\").format(new Date())") String date) {
        Integer categoryId = 0;
        switch (category) {
            case "baseball":
                categoryId = 1;
                break;
            case "soccer":
                categoryId = 2;
                break;
            case "basketball":
                categoryId = 3;
                break;
            case "musical":
                categoryId = 4;
                break;
            case "concert":
                categoryId = 5;
                break;
        }

        events = eventService.findByCategoryId(categoryId);

        model.addAttribute("events", events);
        model.addAttribute("categoryId", categoryId);

        return "calendar";
    }

}

/*
    @GetMapping("/event/test/{date}")
    public String questionCreate(@PathVariable String date) {

        return "calendar";
    }
*/
    /* inputCategory 파라미터를 받아, 미리 저장된 Map에서 해당 카테고리에 맵핑된 객체를 리턴해주는 메소드 */
    /*
@GetMapping("/getEvent")
public Map<String,Object> getEventByCategoryId( @RequestParam Integer categoryId ) {
    Map<String, Integer> ageMap = new HashMap<>();

    events = eventService.findByCategoryId(categoryId);

    for (Event event_ : events)
    ageMap.put(event_);

    Map<String,Object> returnMap = new HashMap<>();
    returnMap.put("name", categoryId);
    returnMap.put("age", ageMap.get(categoryId));

    return returnMap;
}
    */

    /*
       @GetMapping("/event/{category}")
    public String showEvent(Model model, @PathVariable String category, @RequestParam(defaultValue = "new SimpleDateFormat(\"yyyy-MM-dd\").format(new Date())") String date) {
        Integer categoryId = 0;
        switch(category) {
            case "baseball": categoryId = 1;
                break;
            case "soccer": categoryId = 2;
                break;
            case "basketball": categoryId = 3;
                break;
            case "musical": categoryId = 4;
                break;
            case "concert": categoryId = 5;
                break;
        }

        events = eventService.findByCategoryId(categoryId);

        model.addAttribute("events", events);
        model.addAttribute("categoryId",categoryId);

        return "calendar";
    }
     */





