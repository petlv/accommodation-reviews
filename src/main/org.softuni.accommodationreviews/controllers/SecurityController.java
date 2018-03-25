package org.softuni.accommodationreviews.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SecurityController {

    @PostMapping("/logout")
    public ModelAndView logout(@RequestParam(required = false, name = "logout") String logout,
                               ModelAndView modelAndView, RedirectAttributes redirectAttributes) {
        modelAndView.setViewName("redirect:/login");

        if(logout != null) redirectAttributes.addFlashAttribute("logout", logout);

        return modelAndView;
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
