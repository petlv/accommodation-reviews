package org.softuni.accommodationreviews.areas.comments.services;

import org.softuni.accommodationreviews.areas.comments.models.CommentBindingModel;
import org.softuni.accommodationreviews.areas.comments.models.CommentServiceModel;
import org.softuni.accommodationreviews.areas.comments.models.CommentViewModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

    boolean addComment(CommentBindingModel commentBindingModel);

    List<CommentServiceModel> getAllComments();

    List<CommentViewModel> fromServiceToViewModel();

}
