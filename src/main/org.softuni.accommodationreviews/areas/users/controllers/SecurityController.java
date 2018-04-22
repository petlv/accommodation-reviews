package org.softuni.accommodationreviews.areas.users.controllers;

import org.softuni.accommodationreviews.areas.users.models.UserBindingModel;
import org.softuni.accommodationreviews.areas.users.services.UserService;
import org.softuni.accommodationreviews.controllers.BaseController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class SecurityController extends BaseController {

    private final UserService userService;

    public SecurityController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    @PreAuthorize("!isAuthenticated()")
    public ModelAndView register(Model model) {

        if(!model.containsAttribute("registerInput")) {
            model.addAttribute("registerInput", new UserBindingModel());
        }

        return this.view("security/register");
    }

    @PostMapping("/register")
    @PreAuthorize("!isAuthenticated()")
    public ModelAndView registerConfirm(@Valid @ModelAttribute(name = "registerInput")
                                                    UserBindingModel userModel,
                                        @ModelAttribute("optionsRadios") String optionsRadios,
                                        BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult" +
                    ".registerInput", bindingResult);
            redirectAttributes.addFlashAttribute("registerInput", userModel);
            return this.register(model);
        }

        if(!userModel.getPassword().equals(userModel.getConfirmPassword())) {
            return this.register(model);
        }

        if(this.userService.register(userModel, optionsRadios)) {
            return this.redirect("/login");
        } else {
            return this.register(model);
        }
    }

    @GetMapping("/login")
    @PreAuthorize("isAnonymous()")
    public ModelAndView login(@RequestParam(required = false, name = "error") String error,
                              @RequestParam(required = false, name = "logout") String logout,
                              ModelAndView mav) {
        mav.setViewName("security/login");
        if (error != null) {
            mav.addObject("error", error);
        }

        if (logout != null) {
            mav.addObject("logout", logout);
        }

        return mav;
    }

    @PostMapping("/logout")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView logout(@RequestParam(required = false, name = "logout") String logout,
                               ModelAndView modelAndView, RedirectAttributes redirectAttributes) {
        modelAndView.setViewName("redirect:/login");

        if(logout != null) redirectAttributes.addFlashAttribute("logout", logout);

        return modelAndView;
    }

    @GetMapping("/error")
    public ModelAndView error(ModelAndView mav) {
        mav.setViewName("security/error");

        return mav;
    }


    @GetMapping("/unauth")
    @PreAuthorize("isAnonymous()")
    public @ResponseBody String unAuthorizedMethod() {
        return "I am unauthorized method";
    }

    @GetMapping("/auth")
    public @ResponseBody String authorizedMethod() {
        return "I am authorized method";
    }

    @GetMapping("/for_users")
    @PreAuthorize("hasRole('USER')")
    public @ResponseBody String users() {
        return "I am AUTHORIZED method for users";
    }

    @GetMapping("/for_admins")
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody String admins() {
        return "I am AUTHORIZED method for admins";
    }

}
