package org.softuni.accommodationreviews.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @GetMapping("/")
    public ModelAndView index (ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        modelAndView.addObject("message", "Pete");
        return modelAndView;
    }

    @PostMapping("/secured_with_captcha")
    public String secured() {
        return "secured";
    }

}
