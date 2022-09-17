package com.idea5.playwithme.home.controller;

import com.idea5.playwithme.article.service.ArticleService;
import com.idea5.playwithme.event.domain.Event;
import com.idea5.playwithme.event.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class HomeController {
    private final EventService eventService;

    @GetMapping("/")
    public String home(Model model) {
        Integer intg = 1;
        Event baseballTop1 = eventService.findEventTop1ByArticleCount(intg);
        intg = 2;
        Event soccerTop1 = eventService.findEventTop1ByArticleCount(intg);
        intg = 3;
        Event basketballTop1 = eventService.findEventTop1ByArticleCount(intg);
        intg = 4;
        Event musicalTop1 = eventService.findEventTop1ByArticleCount(intg);
        intg = 5;
        Event concertTop1 = eventService.findEventTop1ByArticleCount(intg);

        model.addAttribute("baseballTop1", "baseballTop1");
        model.addAttribute("soccerTop1", "soccerTop1");
        model.addAttribute("basketballTop1", "basketballTop1");
        model.addAttribute("musicalTop1", "musicalTop1");
        model.addAttribute("concertTop1", "concertTop1");


        return "home";
    }
}
