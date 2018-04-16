package org.softuni.accommodationreviews.areas.comments.services;

import org.modelmapper.ModelMapper;
import org.softuni.accommodationreviews.areas.comments.Comment;
import org.softuni.accommodationreviews.areas.comments.CommentRepository;
import org.softuni.accommodationreviews.areas.comments.models.CommentBindingModel;
import org.softuni.accommodationreviews.areas.comments.models.CommentServiceModel;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.mapper = mapper;
    }

    @Override
    public void addComment(CommentBindingModel commentBindingModel) {
        Comment comment = this.mapper.map(commentBindingModel, Comment.class);
        this.commentRepository.save(comment);
    }

    @Override
    public List<CommentServiceModel> getAllComments() {
        ModelMapper modelMapper = new ModelMapper();

        return this.commentRepository
                .findAll()
                .stream()
                .map(x -> modelMapper.map(x, CommentServiceModel.class))
                .collect(Collectors.toList());
    }
}
