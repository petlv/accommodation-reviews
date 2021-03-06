package org.softuni.accommodationreviews.controllers;

import org.softuni.accommodationreviews.areas.cloud.CloudImageExtractor;
import org.softuni.accommodationreviews.areas.cloud.CloudImageUploader;
import org.softuni.accommodationreviews.areas.towns.services.TownService;
import org.softuni.accommodationreviews.areas.users.services.UserService;
import org.softuni.accommodationreviews.models.service.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController extends BaseController {

    private TownService townService;

    private final CloudImageExtractor cloudImageExtractor;
    private final CloudImageUploader cloudImageUploader;

    @Autowired
    public HomeController(TownService townService, UserService userService, CloudImageExtractor cloudImageExtractor,
                          CloudImageUploader cloudImageUploader) {
        this.townService = townService;
        this.cloudImageExtractor = cloudImageExtractor;
        this.cloudImageUploader = cloudImageUploader;
    }

    @GetMapping(value = {"/", "/home"})
    public ModelAndView index() {
        //return this.view("home/index", "personName", "Pete");
        return this.view("home/index");
    }

    @GetMapping("/map")
    public ModelAndView map() {
        return this.view("home/map");
    }

    @GetMapping("/images")
    public ModelAndView images() {

        List<Image> images = new ArrayList<Image>();

        try {
            images = this.cloudImageExtractor.getAllImages();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this.view("home/images", "imageData", images);

    }

    @PostMapping("/images")
    public ModelAndView images(@RequestParam("file")MultipartFile file) {

        try {
            this.cloudImageUploader.uploadFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this.view("home/images");
    }

    @GetMapping("/about")
    public ModelAndView test() {
        return this.view("home/about");
    }


    @GetMapping("/search")
    public ModelAndView search(@RequestParam(name = "name") String name, ModelAndView modelAndView) {
        modelAndView.setViewName("index");

        if ((name.isEmpty())) {
            modelAndView.addObject("towns", this.townService.getAllTowns());
        } else {
            modelAndView.addObject("towns",
                    this.townService.getByName(name));
        }
        return modelAndView;
    }

}
