package org.softuni.accommodationreviews.areas.accommodations;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/accommodation")
public class AccommodationController {

    /*private AccommodationService accommodationService;
    private TownService townService;

    public AccommodationController(AccommodationService accommodationService, TownService townService) {
        this.accommodationService = accommodationService;
        this.townService = townService;
    }

    @GetMapping("/add")
    public ModelAndView addAccommodation(ModelAndView mav) {
        mav.setViewName("add-accommodation");

        AddAccommodationViewModel viewModel = new AddAccommodationViewModel();
        for (TownBindingModel townServiceModel : this.townService.getAllTowns()) {
            viewModel.getTowns().add(townServiceModel.getName());
        }

        mav.addObject("model", viewModel);

        return mav;
    }*/

}
