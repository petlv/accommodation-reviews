package org.softuni.accommodationreviews.areas.users.controllers;

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

    public AjaxControllers(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comments")
    public String loadRecentComments() {
        List<CommentViewModel> viewModel = this.commentService.fromServiceToViewModel();
        StringBuilder commentDescriptions = new StringBuilder();
        for (CommentViewModel commentViewModel : viewModel) {
            commentDescriptions.append("&#9725; ");
            commentDescriptions.append(commentViewModel.getDescription());
            commentDescriptions.append(";").append(System.getProperty("line.separator"));
        }
        return commentDescriptions.toString();
    }
}
