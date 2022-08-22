package com.idea5.playwithme.calendar.domain.controller;

import com.idea5.playwithme.event.domain.Event;
import com.idea5.playwithme.event.domain.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
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
    public String showEvent(Model model, @RequestParam String category, @RequestParam(defaultValue = "new SimpleDateFormat(\"yyyy-MM-dd\").format(new Date())") String date) {
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
    @GetMapping("/getEvent")
    @ResponseBody
    public List<Event> getEvent(Model model, @RequestParam String category,  @RequestParam(defaultValue = "0")String date ) {
        LocalDate searchDate;

        if(date.equals("0")){ // 아무것도 입력하지 않았을 때는 당일 날짜로 고정
            searchDate = LocalDate.now();
        }
        else{ // 입력한 String형의 데이터를 localdate로 변환
            int [] dateInfo = Arrays.stream(date.split("-"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            searchDate =  LocalDate.of(dateInfo[0],dateInfo[1],dateInfo[2]);
        }

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

        events = eventService.getEventsByCategoryAndDate(categoryId, searchDate);

        return events;
    }
}
    /* 단일 데이터 event는 JSON 헝태로 잘 전송됨
    @GetMapping("/getEvent")
    @ResponseBody
    public Event getEvent(Model model, @RequestParam String category, @RequestParam(defaultValue = "new SimpleDateFormat(\"yyyy-MM-dd\").format(new Date())") String date ) {
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
        Event event = new Event();
        event.setId(3L);
        event.setCategoryId(1);
        event.setName("KBA경기");
        event.setLocation("서울");
        event.setDate(LocalDateTime.of(2022,8,15,00,00,00));
        return event;
    }
    */


    /*
@RequestMapping("/getJson")
@ResponseBody
public String eventToJson(HttpServletResponse rs, Model model){
    try {
        rs.setContentType("text/html;charset=utf-8");
        PrintWriter out = rs.getWriter();
        return eventService.eventDtoMapToJsonStr();
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

}
*/
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





