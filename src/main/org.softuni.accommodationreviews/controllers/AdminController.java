package org.softuni.accommodationreviews.controllers;

import org.softuni.accommodationreviews.models.binding.TownBindingModel;
import org.softuni.accommodationreviews.services.JsonWriter;
import org.softuni.accommodationreviews.services.TownService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final TownService townService;
    private final JsonWriter jsonWriter;

    public AdminController(TownService townService, JsonWriter jsonWriter) {
        this.townService = townService;
        this.jsonWriter = jsonWriter;
    }

    @GetMapping("/towns")
    @PreAuthorize("isAnonymous()")
    public ModelAndView showTowns (ModelAndView mav) {
        mav.setViewName("admin/town-list");
        return mav;
    }

    @GetMapping("/town-add")
    public ModelAndView addTown (ModelAndView mav) {
        mav.setViewName("admin/town-add");
        return mav;
    }

    @PostMapping("/town-add")
    @PreAuthorize("isAnonymous()")
    public ModelAndView addTownConfirm (@ModelAttribute TownBindingModel townModel,
                                        ModelAndView mav) {
        mav.setViewName("redirect:/home");
        this.townService.addTown(townModel);
        this.jsonWriter.jsonInString();

        return mav;
    }

}
