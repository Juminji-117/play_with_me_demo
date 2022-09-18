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



        return "home";
    }
}
