package org.softuni.accommodationreviews.areas.towns;

import org.softuni.accommodationreviews.areas.towns.models.TownBindingModel;
import org.softuni.accommodationreviews.areas.towns.services.TownService;
import org.softuni.accommodationreviews.areas.towns.services.TownWriterToJson;
import org.softuni.accommodationreviews.controllers.BaseController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/town")
public class TownController extends BaseController {

    private final TownRepository townRepository;
    private final TownService townService;
    private final TownWriterToJson townWriterToJson;

    public TownController(TownRepository townRepository, TownService townService, TownWriterToJson townWriterToJson) {
        this.townRepository = townRepository;
        this.townService = townService;
        this.townWriterToJson = townWriterToJson;
    }

    @GetMapping("/show")
    public ModelAndView showTowns () {

        return this.view("town/town-list", "towns", this.townRepository.findAll());

    }

    @GetMapping("/add-town")
    public ModelAndView addTown () {

        return this.view("town/town-add");

    }

    @PostMapping("/add-town")
    @PreAuthorize("isAnonymous()")
    public ModelAndView addTownConfirm (@ModelAttribute TownBindingModel townModel) {

        this.townService.addTown(townModel);
        this.townWriterToJson.writeTownsInJson();

        return this.redirect("/town/show");
    }

}
