package org.softuni.accommodationreviews.areas.comments.services;

import org.softuni.accommodationreviews.areas.comments.models.CommentBindingModel;
import org.softuni.accommodationreviews.areas.comments.models.CommentServiceModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

    void addComment(CommentBindingModel commentBindingModel);

    List<CommentServiceModel> getAllComments();

}
