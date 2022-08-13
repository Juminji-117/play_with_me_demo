package com.idea5.playwithme.calendar.domain.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class CalendarController_demo {

    @GetMapping("/template")
    public String main(){

        return "index";
    }
}
