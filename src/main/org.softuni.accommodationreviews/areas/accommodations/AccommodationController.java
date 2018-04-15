package org.softuni.accommodationreviews.areas.accommodations;

import org.softuni.accommodationreviews.areas.accommodations.models.AccommodationBindingModel;
import org.softuni.accommodationreviews.areas.accommodations.models.AddAccommodationViewModel;
import org.softuni.accommodationreviews.areas.accommodations.services.AccommodationService;
import org.softuni.accommodationreviews.areas.towns.models.TownServiceModel;
import org.softuni.accommodationreviews.areas.towns.services.TownService;
import org.softuni.accommodationreviews.controllers.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/accommodation")
public class AccommodationController extends BaseController {

    private final TownService townService;
    private final AccommodationService accommodationService;

    public AccommodationController(TownService townService, AccommodationService accommodationService) {
        this.townService = townService;
        this.accommodationService = accommodationService;
    }

    @GetMapping("/add")
    public ModelAndView addVirus(Model model) {

        if(!model.containsAttribute("accommodationInput")) {
            model.addAttribute("accommodationInput", new AccommodationBindingModel());
        }

        AddAccommodationViewModel viewModel = new AddAccommodationViewModel();

        for (TownServiceModel townServiceModel : this.townService.getAllTowns()) {
            viewModel.getTowns().add(townServiceModel.getTitle());
        }

        return this.view("accommodation/add-accommodation", "accommodationViewModel", viewModel);
    }

    @PostMapping("/add")
    public ModelAndView addVirusConfirm(@Valid @ModelAttribute(name = "accommodationInput")
                                                    AccommodationBindingModel accommodationBindingModel,
                                        BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.accommodationInput",
                    bindingResult);
            redirectAttributes.addFlashAttribute("accommodationInput", accommodationBindingModel);

            return this.redirect("/add");

        } else {
            this.accommodationService.createVirus(accommodationBindingModel);
            return this.redirect("/show");
        }
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editVirus(@PathVariable String id, ModelAndView modelAndView, Model model, ModelMapper modelMapper) {
        VirusServiceModel virusById = this.virusService.getById(id);

        modelAndView.setViewName("add-virus");

        if(!model.containsAttribute("virusInput")) {
            AddVirusBindingModel bindingModel = modelMapper.map(virusById, AddVirusBindingModel.class);

            model.addAttribute("virusInput", bindingModel);
        }

        AddVirusViewModel viewModel = new AddVirusViewModel();

        viewModel.setId(virusById.getId());

        for (CapitalServiceModel capitalServiceModel : this.capitalService.getAllCapitals()) {
            viewModel.getCapitals().add(capitalServiceModel.getName());
        }

        modelAndView.addObject("virusViewModel", viewModel);

        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editVirusConfirm(@PathVariable String id, @Valid @ModelAttribute(name = "virusInput") AddVirusBindingModel addVirusBindingModel, BindingResult bindingResult, ModelAndView modelAndView, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.virusInput", bindingResult);
            redirectAttributes.addFlashAttribute("virusInput", addVirusBindingModel);

            modelAndView.setViewName("redirect:add");
        } else {
            this.virusService.editVirus(id, addVirusBindingModel);
            modelAndView.setViewName("redirect:/viruses/show");
        }

        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteVirus(@PathVariable String id, ModelAndView modelAndView, Model model, ModelMapper modelMapper) {
        VirusServiceModel virusById = this.virusService.getById(id);

        modelAndView.setViewName("add-virus");

        model.addAttribute("virusInput", modelMapper.map(virusById, AddVirusBindingModel.class));

        AddVirusViewModel viewModel = new AddVirusViewModel();
        viewModel.setId(virusById.getId());

        for (CapitalServiceModel capitalServiceModel : this.capitalService.getAllCapitals()) {
            viewModel.getCapitals().add(capitalServiceModel.getName());
        }

        modelAndView.addObject("virusViewModel", viewModel);

        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView deleteConfirm(@PathVariable String id, ModelAndView modelAndView) {
        this.virusService.deleteVirus(id);

        modelAndView.setViewName("redirect:/viruses/show");

        return modelAndView;
    }

    @GetMapping("/show")
    public ModelAndView showViruses(ModelAndView modelAndView) {
        modelAndView.setViewName("show-viruses");

        modelAndView.addObject("viruses", this.virusService.getAllViruses());

        return modelAndView;
    }
}
