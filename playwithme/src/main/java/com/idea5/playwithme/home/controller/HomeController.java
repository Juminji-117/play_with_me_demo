package com.idea5.playwithme.home.controller;

import com.idea5.playwithme.article.service.ArticleService;
import com.idea5.playwithme.event.domain.Event;
import com.idea5.playwithme.event.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@AllArgsConstructor
@Controller
public class HomeController {
    private final EventService eventService;

    @GetMapping("/")
    public String home(Model model) {

        Event baseballTop1 = eventService.findTopEventByArticleCount(1);
        Event soccerTop1 = eventService.findTopEventByArticleCount(2);
        Event basketballTop1 = eventService.findTopEventByArticleCount(3);
        Event musicalTop1 = eventService.findTopEventByArticleCount(4);
        Event concertTop1 = eventService.findTopEventByArticleCount(5);



        model.addAttribute("baseballTop1", eventService.findTopEventByArticleCount(1));
        model.addAttribute("soccerTop1", soccerTop1);
        model.addAttribute("basketballTop1", basketballTop1);
        model.addAttribute("musicalTop1", eventService.findTopEventByArticleCount(4));
        model.addAttribute("concertTop1", concertTop1);


        return "home";
    }
}
