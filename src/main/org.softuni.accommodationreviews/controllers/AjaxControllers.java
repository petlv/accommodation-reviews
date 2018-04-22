package org.softuni.accommodationreviews.controllers;

import org.softuni.accommodationreviews.areas.accommodations.models.AccommodationViewModel;
import org.softuni.accommodationreviews.areas.accommodations.services.AccommodationService;
import org.softuni.accommodationreviews.areas.comments.models.CommentViewModel;
import org.softuni.accommodationreviews.areas.comments.services.CommentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ajax")
public class AjaxControllers {

    private final CommentService commentService;
    private final AccommodationService accommodationService;

    public AjaxControllers(CommentService commentService, AccommodationService accommodationService) {
        this.commentService = commentService;
        this.accommodationService = accommodationService;
    }

    @GetMapping("/accommodations")
    public String loadRecentAccommodations() {
        List<AccommodationViewModel> viewModel = this.accommodationService.fromServiceToViewModel();

        StringBuilder descriptions = new StringBuilder();
        for (int i = 0; i < viewModel.size(); i++) {
            if (i == 0) {
                descriptions.append("<ul>").append(System.getProperty("line.separator"));
            }
            descriptions.append("<li>").append(viewModel.get(i).getName())
                    .append("</li>").append(System.getProperty("line.separator"));
            if (i == viewModel.size()) {
                descriptions.append("</ul>").append(System.getProperty("line.separator"));
            }
        }

        return descriptions.toString();
    }

    @GetMapping("/comments")
    public String loadRecentComments() {
        List<CommentViewModel> viewModel = this.commentService.fromServiceToViewModel();
        StringBuilder commentDescriptions = new StringBuilder();
        for (int i = 0; i < viewModel.size(); i++) {
             if (i == 0) {
                 commentDescriptions.append("<ul>").append(System.getProperty("line.separator"));
             }
             commentDescriptions.append("<li>").append(viewModel.get(i).getDescription())
                     .append("</li>").append(System.getProperty("line.separator"));
            if (i == viewModel.size()) {
                commentDescriptions.append("</ul>").append(System.getProperty("line.separator"));
            }
        }

        return commentDescriptions.toString();
    }

}
