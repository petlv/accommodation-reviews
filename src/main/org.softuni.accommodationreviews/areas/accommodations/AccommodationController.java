package org.softuni.accommodationreviews.areas.accommodations;

import org.modelmapper.ModelMapper;
import org.softuni.accommodationreviews.areas.accommodations.models.AccommodationBindingModel;
import org.softuni.accommodationreviews.areas.accommodations.models.AccommodationServiceModel;
import org.softuni.accommodationreviews.areas.accommodations.models.AccommodationViewModel;
import org.softuni.accommodationreviews.areas.accommodations.models.ShowAccViewModel;
import org.softuni.accommodationreviews.areas.accommodations.services.AccommodationService;
import org.softuni.accommodationreviews.areas.comments.models.CommentServiceModel;
import org.softuni.accommodationreviews.areas.comments.services.CommentService;
import org.softuni.accommodationreviews.areas.towns.services.TownService;
import org.softuni.accommodationreviews.controllers.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/accommodation")
public class AccommodationController extends BaseController {

    private final TownService townService;
    private final AccommodationService accommodationService;
    private final CommentService commentService;

    public AccommodationController(TownService townService, AccommodationService accommodationService, CommentService
            commentService) {
        this.townService = townService;
        this.accommodationService = accommodationService;
        this.commentService = commentService;
    }

    @GetMapping("/add")
    public ModelAndView addAccommodation(Model model) {

        return this.view("accommodation/add-accommodation", "towns", this.townService.getAllTowns());

        /*if(!model.containsAttribute("accommodationInput")) {
            model.addAttribute("accommodationInput", new AccommodationBindingModel());
        }

        AccommodationViewModel viewModel = new AccommodationViewModel();

        for (TownServiceModel townServiceModel : this.townService.getAllTowns()) {
            viewModel.getTowns().add(townServiceModel.getTitle());
        }

        return this.view("accommodation/add-accommodation", "accommodationViewModel", viewModel);*/
    }

    @PostMapping("/add")
    public ModelAndView addAccommodationConfirm(@Valid @ModelAttribute(name = "accommodationInput")
                                                    AccommodationBindingModel accommodationBindingModel,
                                        BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.accommodationInput",
                    bindingResult);
            redirectAttributes.addFlashAttribute("accommodationInput", accommodationBindingModel);

            return this.redirect("/accommodation/add");

        } else {
            this.accommodationService.createAccommodation(accommodationBindingModel);
            return this.redirect("/accommodation/show");
        }
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editAccommodation(@PathVariable Long id, Model model, ModelMapper modelMapper) {
        AccommodationServiceModel accommodationById = this.accommodationService.getById(id);

        if(!model.containsAttribute("accommodationInput")) {
            AccommodationBindingModel bindingModel = modelMapper.map(accommodationById, AccommodationBindingModel.class);

            model.addAttribute("accommodationInput", bindingModel);
        }

        AccommodationViewModel viewModel = new AccommodationViewModel();

        viewModel.setId(accommodationById.getId());

        return this.view("accommodation/add-accommodation", "accommodationModel", viewModel);
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editAccommodationConfirm(@PathVariable Long id, @Valid @ModelAttribute(name = "virusInput")
            AccommodationBindingModel accommodationBindingModel, BindingResult bindingResult,
                                         RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.accommodationInput",
                    bindingResult);
            redirectAttributes.addFlashAttribute("accommodationInput", accommodationBindingModel);

            return this.redirect("/add");

        } else {
            this.accommodationService.editAccommodation(id, accommodationBindingModel);
            return this.redirect("accommodation/show");
        }
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteAccommodation(@PathVariable Long id, Model model, ModelMapper modelMapper) {

        AccommodationServiceModel accommodationById = this.accommodationService.getById(id);

        model.addAttribute("accommodationInput", modelMapper.map(accommodationById, AccommodationBindingModel.class));

        AccommodationViewModel viewModel = new AccommodationViewModel();
        viewModel.setId(accommodationById.getId());

        for (CommentServiceModel commentServiceModel : this.commentService.getAllComments()) {
            viewModel.getComments().add(commentServiceModel.getDescription());
        }

        return this.view("accommodation/add-accommodation", "accommodationViewModel", viewModel);
    }

    @PostMapping("/delete/{id}")
    public ModelAndView deleteConfirm(@PathVariable Long id) {
        this.accommodationService.deleteAccommodation(id);

        return this.redirect("accommodation/show");
    }

    @GetMapping("/show")
    public ModelAndView showAccommodations() {

        ModelMapper mapper = new ModelMapper();
        List<AccommodationViewModel> listAcm = this.accommodationService.fromServiceToViewModel();
        List<ShowAccViewModel> listScvm = new ArrayList<>();

        for (AccommodationViewModel acm : listAcm) {
            ShowAccViewModel scvm = mapper.map(acm, ShowAccViewModel.class);
            scvm.setComments(String.join("; ", acm.getComments()));
            listScvm.add(scvm);
        }

        return this.view("accommodation/show-accommodations",
                "accommodations", listScvm);
    }

    @GetMapping("/show-my")
    public ModelAndView showMyAccommodations(Principal principal) {

        ModelMapper mapper = new ModelMapper();
        List<AccommodationViewModel> listViewModel = this.accommodationService.fromServiceToViewModel();
        List<ShowAccViewModel> myAccommodations = new ArrayList<>();
        for (AccommodationViewModel viewModel : listViewModel) {
            if (viewModel.getUser().equals(principal.getName())) {
                ShowAccViewModel savm = mapper.map(viewModel, ShowAccViewModel.class);
                savm.setComments(String.join("; ", viewModel.getComments()));
                myAccommodations.add(savm);
            }
        }

        return this.view("accommodation/show-accommodations",
                "accommodations", myAccommodations);
    }

    @GetMapping("/comment/{id}")
    public ModelAndView commentAccommodation(@PathVariable Long id, Model model, ModelMapper modelMapper) {

        AccommodationServiceModel accommodationById = this.accommodationService.getById(id);

        if(!model.containsAttribute("accommodationInput")) {
            AccommodationBindingModel bindingModel = modelMapper.map(accommodationById, AccommodationBindingModel.class);

            model.addAttribute("accommodationInput", bindingModel);
        }

        AccommodationViewModel viewModel = new AccommodationViewModel();

        viewModel.setId(accommodationById.getId());

        return this.view("accommodation/comment-accommodation", "accommodationModel", viewModel);
    }
}
