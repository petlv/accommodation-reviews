package org.softuni.accommodationreviews.controllers;

import org.softuni.accommodationreviews.models.ExcludeCaptcha;
import org.softuni.accommodationreviews.models.binding.CaptchaBindingModel;
import org.softuni.accommodationreviews.models.view.OwnerRegisterRequestModel;
import org.softuni.accommodationreviews.models.view.TouristRegisterRequestModel;
import org.softuni.accommodationreviews.services.CheckOwnerOrTourist;
import org.softuni.accommodationreviews.services.TouristService;
import org.softuni.accommodationreviews.services.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    //private AccommodationService accommodationService;
    private TownService townService;
    private final CheckOwnerOrTourist checkOwnerOrTourist;

    @Autowired
    public HomeController(TownService townService, TouristService touristService, CheckOwnerOrTourist
            checkOwnerOrTourist) {
        this.townService = townService;
        this.checkOwnerOrTourist = checkOwnerOrTourist;
    }

    @GetMapping("/")
    public ModelAndView index (ModelAndView modelAndView) {
        modelAndView.setViewName("/home/index");
        modelAndView.addObject("message", "Pete");
        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView register() {

        return new ModelAndView("home/register");
    }

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView register(@ModelAttribute TouristRegisterRequestModel touristModel,
                                 @ModelAttribute OwnerRegisterRequestModel ownerModel,
                                 @ModelAttribute("optionsRadios") String optionsRadios) {
        this.checkOwnerOrTourist.register(touristModel, ownerModel, optionsRadios);
        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("home/login");
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
