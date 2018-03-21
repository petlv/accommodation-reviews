package org.softuni.accommodationreviews.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestSecurityController {

    @GetMapping("/unauth")
    @PreAuthorize("isAnonymous()")
    public @ResponseBody String unAuthorizedMethod() {
        return "I am unauthorized method";
    }

    @GetMapping("/auth")
    public @ResponseBody String authorizedMethod() {
        return "I am authorized method";
    }

}
