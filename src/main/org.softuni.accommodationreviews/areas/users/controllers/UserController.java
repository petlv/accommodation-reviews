package org.softuni.accommodationreviews.areas.users.controllers;

import org.softuni.accommodationreviews.areas.users.UserRepository;
import org.softuni.accommodationreviews.areas.users.models.UserServiceModel;
import org.softuni.accommodationreviews.areas.users.models.UserViewModel;
import org.softuni.accommodationreviews.areas.users.services.UserService;
import org.softuni.accommodationreviews.controllers.BaseController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

//@RestController
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/profile-user/{username}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView showProfile(@PathVariable String username) {
        UserServiceModel userDetails = this.userService.findByUsernameCustom(username);
        return this.view("users/show-profile", "currentUser", userDetails);
    }

    @GetMapping("/edit-user/{username}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editProfile(@PathVariable String username) {
        UserServiceModel userDetails = this.userService.findByUsernameCustom(username);
        return this.view("users/edit-profile", "currentUser", userDetails);
    }

    @PostMapping("/edit-user/{username}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView confirmEditProfile(@ModelAttribute UserViewModel model, @PathVariable String username) {
        UserServiceModel userDetails = this.userService.findByUsernameCustom(username);
        if (this.userService.edit(model, userDetails.getUsername())) {
            return this.redirect("/");
        }
        return this.editProfile(username);
    }

}
