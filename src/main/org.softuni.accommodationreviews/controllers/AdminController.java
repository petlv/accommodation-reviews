package org.softuni.accommodationreviews.controllers;

import org.softuni.accommodationreviews.areas.towns.models.TownBindingModel;
import org.softuni.accommodationreviews.areas.towns.TownRepository;
import org.softuni.accommodationreviews.areas.towns.services.TownWriterToJson;
import org.softuni.accommodationreviews.areas.towns.services.TownService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

    private final TownRepository townRepository;
    private final TownService townService;
    private final TownWriterToJson townWriterToJson;

    public AdminController(TownRepository townRepository, TownService townService, TownWriterToJson townWriterToJson) {
        this.townRepository = townRepository;
        this.townService = townService;
        this.townWriterToJson = townWriterToJson;
    }

    @GetMapping("/towns")
    @PreAuthorize("isAnonymous()")
    public ModelAndView showTowns () {

        return this.view("admin/town-list", "towns", this.townRepository.findAll());

    }

    @GetMapping("/town-add")
    public ModelAndView addTown () {

        return this.view("admin/town-add");

    }

    @PostMapping("/town-add")
    @PreAuthorize("isAnonymous()")
    public ModelAndView addTownConfirm (@ModelAttribute TownBindingModel townModel) {

        this.townService.addTown(townModel);
        this.townWriterToJson.writeTownsInJson();

        return this.redirect("/");
    }

}
