package org.softuni.accommodationreviews.areas.users;

import org.softuni.accommodationreviews.areas.users.services.UserService;
import org.softuni.accommodationreviews.controllers.BaseController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

//@RestController
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView profile(Principal principal) {
        UserDetails userDetails = this.userService.loadUserByUsername(principal.getName());
        return this.view("users/profile", "currentUser", userDetails);
    }

}
