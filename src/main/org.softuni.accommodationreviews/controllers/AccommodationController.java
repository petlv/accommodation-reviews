package org.softuni.accommodationreviews.controllers;

import org.softuni.accommodationreviews.models.service.TownServiceModel;
import org.softuni.accommodationreviews.models.view.AddAccommodationViewModel;
import org.softuni.accommodationreviews.services.AccommodationService;
import org.softuni.accommodationreviews.services.TownService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/accommodation")
public class AccommodationController {

    private AccommodationService accommodationService;
    private TownService townService;

    public AccommodationController(AccommodationService accommodationService, TownService townService) {
        this.accommodationService = accommodationService;
        this.townService = townService;
    }

    @GetMapping("/add")
    public ModelAndView addAccommodation(ModelAndView mav) {
        mav.setViewName("add-accommodation");

        AddAccommodationViewModel viewModel = new AddAccommodationViewModel();
        for (TownServiceModel townServiceModel : this.townService.getAllTowns()) {
            viewModel.getTowns().add(townServiceModel.getName());
        }

        mav.addObject("model", viewModel);

        return mav;
    }

}
