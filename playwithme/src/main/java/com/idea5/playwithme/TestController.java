package com.idea5.playwithme;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TestController {
//    @GetMapping("/")
//    public String test(Model model) {
//        model.addAttribute("data", "test page");
//        return "test";
//    }

    // TODO: 모집 인원 확정 폼(임시 추후 옮겨야함)
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/recruit/{board_id}/{article_id}")
    public String recruitConfirmForm(@PathVariable("board_id") Long boardId, @PathVariable("article_id") Long articleId) {
        return "recruit_confirm_form";
    }
@GetMapping("/about")
    public String about () {
        return "about";
}

    @GetMapping("/blog")
    public String blog () {
        return "blog";
    }
    @GetMapping("/contact")
    public String contact () {
        return "contact";
    }

    @GetMapping("/elements")
    public String elements () {
        return "elements";
    }

    @GetMapping("/index")
    public String index () {
        return "index";
    }

    @GetMapping("/portfolio")
    public String portfolio () {
        return "portfolio";
    }

    @GetMapping("/single")
    public String single () {
        return "single";
    }
}

