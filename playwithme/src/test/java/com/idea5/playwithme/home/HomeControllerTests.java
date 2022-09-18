package com.idea5.playwithme.home;

import com.idea5.playwithme.event.domain.Event;
import com.idea5.playwithme.event.service.EventService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class HomeControllerTests {
    @Autowired
    EventService eventService;



    @Test
    public void Query_DSL로_결과_제대로_가져오는지 ()
    {
        Event baseballTop1 = eventService.findTopEventByArticleCount(1);
        Event soccerTop1 = eventService.findTopEventByArticleCount(2);
        Event basketballTop1 = eventService.findTopEventByArticleCount(3);
        Event musicalTop1 = eventService.findTopEventByArticleCount(4);
        Event concertTop1 = eventService.findTopEventByArticleCount(5);
        Assertions.assertEquals(baseballTop1.getLocation(), "잠실종합운동장 내 보조경기장"); // true
        Assertions.assertEquals(baseballTop1.getLocation(), "잠실종합운동장 내 보조경기장이"); // false


    }
}
