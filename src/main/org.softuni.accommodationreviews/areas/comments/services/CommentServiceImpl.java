package org.softuni.accommodationreviews.areas.comments.services;

import org.modelmapper.ModelMapper;
import org.softuni.accommodationreviews.areas.accommodations.Accommodation;
import org.softuni.accommodationreviews.areas.accommodations.AccommodationRepository;
import org.softuni.accommodationreviews.areas.comments.Comment;
import org.softuni.accommodationreviews.areas.comments.CommentRepository;
import org.softuni.accommodationreviews.areas.comments.models.CommentBindingModel;
import org.softuni.accommodationreviews.areas.comments.models.CommentServiceModel;
import org.softuni.accommodationreviews.areas.comments.models.CommentViewModel;
import org.softuni.accommodationreviews.areas.users.User;
import org.softuni.accommodationreviews.areas.users.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ModelMapper mapper;
    private final UserRepository userRepository;
    private final AccommodationRepository accommodationRepository;

    public CommentServiceImpl(CommentRepository commentRepository, ModelMapper mapper, UserRepository userRepository,
                              AccommodationRepository accommodationRepository) {
        this.commentRepository = commentRepository;
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.accommodationRepository = accommodationRepository;
    }

    @Override
    public boolean addComment(CommentBindingModel commentBindingModel) {
        Comment comment = this.mapper.map(commentBindingModel, Comment.class);
        User user = this.userRepository.findFirstByUsername(commentBindingModel.getUser());
        Accommodation accommodation = this.accommodationRepository.findByName(commentBindingModel.getAccommodation());
        comment.setCommentUser(user);
        comment.setCommentAccommodation(accommodation);
        this.commentRepository.save(comment);

        user.getUserComments().add(comment);
        this.userRepository.save(user);

        accommodation.getAccommodationComments().add(comment);
        this.commentRepository.save(comment);
        return true;
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

    @Override
    public List<CommentViewModel> fromServiceToViewModel() {
        List<CommentServiceModel> serviceModels = this.getAllComments();
        List<CommentViewModel> viewModel = new ArrayList<>();
        for (CommentServiceModel serviceModel : serviceModels) {
            CommentViewModel currentViewModel = new CommentViewModel();
            currentViewModel.setId(serviceModel.getId());
            currentViewModel.setDescription(serviceModel.getDescription());
            currentViewModel.setCommentUser(serviceModel.getCommentUser().getUsername());
            currentViewModel.setCommentAccommodation(serviceModel.getCommentAccommodation().getName());
            viewModel.add(currentViewModel);
        }
        return viewModel;
    }
}
