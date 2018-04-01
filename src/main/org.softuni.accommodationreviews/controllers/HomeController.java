package org.softuni.accommodationreviews.controllers;

import org.softuni.accommodationreviews.models.ExcludeCaptcha;
import org.softuni.accommodationreviews.models.binding.CaptchaBindingModel;
import org.softuni.accommodationreviews.services.TownService;
import org.softuni.accommodationreviews.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController {

    private TownService townService;

    @Autowired
    public HomeController(TownService townService, UserService userService) {
        this.townService = townService;
    }

    @GetMapping(value = {"/", "/home"})
    public ModelAndView index() {
        //return this.view("home/index", "personName", "Pete");
        return this.view("home/index");
    }

    @GetMapping("/map")
    public ModelAndView map() {
        return this.view("home/map");
    }

    @GetMapping("/test")
    public ModelAndView test() {
        return this.view("home/test");
    }


    @GetMapping("/search")
    public ModelAndView search(@RequestParam(name = "name") String name, ModelAndView modelAndView) {
        modelAndView.setViewName("index");

        if ((name.isEmpty())) {
            modelAndView.addObject("towns", this.townService.getAllTowns());
        } else {
            modelAndView.addObject("towns",
                    this.townService.getByName(name));
        }
        return modelAndView;
    }

    @PostMapping("/secured_with_captcha")
    public String secured(CaptchaBindingModel bindingModel, Model model) {
        model.addAttribute("username", bindingModel.getUsername());
        return "secured";
    }

    @ExcludeCaptcha
    @PostMapping("/not_secured_with_captcha")
    public String notSecured(CaptchaBindingModel bindingModel, Model model) {
        model.addAttribute("username", bindingModel.getUsername());
        return "not_secured";
    }

}
