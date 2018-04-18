package org.softuni.accommodationreviews.areas.users;

import org.softuni.accommodationreviews.areas.users.models.UserViewModel;
import org.softuni.accommodationreviews.areas.users.services.UserService;
import org.softuni.accommodationreviews.controllers.BaseController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

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

    @GetMapping("/show-profile")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView showProfile(Principal principal) {
        User userDetails = this.userRepository.findFirstByUsername(principal.getName());
        return this.view("users/show-profile", "currentUser", userDetails);
    }

    @GetMapping("/edit-profile")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editProfile(Principal principal) {
        User userDetails = this.userRepository.findFirstByUsername(principal.getName());
        return this.view("users/edit-profile", "currentUser", userDetails);
    }

    @PostMapping("/edit-profile")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView confirmEditProfile(UserViewModel model, Principal principal) {
        if (this.userService.edit(model, principal.getName())) {
            return this.redirect("/");
        }
        return this.confirmEditProfile(model, principal);
    }

}
