package org.softuni.accommodationreviews.areas.comments;

import org.softuni.accommodationreviews.areas.accommodations.models.AccommodationServiceModel;
import org.softuni.accommodationreviews.areas.accommodations.services.AccommodationService;
import org.softuni.accommodationreviews.areas.comments.models.CommentBindingModel;
import org.softuni.accommodationreviews.areas.comments.services.CommentService;
import org.softuni.accommodationreviews.controllers.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController extends BaseController {

    private final CommentService commentService;
    private final CommentRepository commentRepository;
    private final AccommodationService accommodationService;

    public CommentController(CommentService commentService, CommentRepository commentRepository, AccommodationService
            accommodationService) {
        this.commentService = commentService;
        this.commentRepository = commentRepository;
        this.accommodationService = accommodationService;
    }

    @GetMapping("/show-my")
    public ModelAndView myComments() {

        return this.view("comment/my-comments",
                "comments", this.commentService.getAllComments());
    }

    @GetMapping("/show-all")
    public ModelAndView allComments() {

        return this.view("comment/show-comments",
                "comments", this.commentService.fromServiceToViewModel());
    }

    @GetMapping("/add-comment")
    public ModelAndView addComment() {

        List<AccommodationServiceModel> allAccommodation = this.accommodationService.getAllAccommodations();

        return this.view("comment/comment-add", "accommodations", allAccommodation);
    }

    @PostMapping("/add-comment")
    public ModelAndView addTownConfirm (@ModelAttribute CommentBindingModel commentModel) {

        this.commentService.addComment(commentModel);

        return this.redirect("/comment/show-my");
    }

}
