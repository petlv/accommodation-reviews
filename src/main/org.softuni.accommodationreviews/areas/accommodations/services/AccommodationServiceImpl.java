package org.softuni.accommodationreviews.areas.accommodations.services;

import org.modelmapper.ModelMapper;
import org.softuni.accommodationreviews.areas.accommodations.Accommodation;
import org.softuni.accommodationreviews.areas.accommodations.AccommodationRepository;
import org.softuni.accommodationreviews.areas.accommodations.models.AccommodationBindingModel;
import org.softuni.accommodationreviews.areas.accommodations.models.AccommodationServiceModel;
import org.softuni.accommodationreviews.areas.accommodations.models.AccommodationViewModel;
import org.softuni.accommodationreviews.areas.comments.Comment;
import org.softuni.accommodationreviews.areas.towns.TownRepository;
import org.softuni.accommodationreviews.areas.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccommodationServiceImpl implements AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final TownRepository townRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Autowired
    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, TownRepository townRepository,
                                    UserRepository userRepository, ModelMapper mapper) {
        this.accommodationRepository = accommodationRepository;
        this.townRepository = townRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public List<AccommodationServiceModel> getAllAccommodations() {
        ModelMapper modelMapper = new ModelMapper();

        return this.accommodationRepository
                .findAll()
                .stream()
                .map(x -> modelMapper.map(x, AccommodationServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public AccommodationServiceModel getById(Long id) {
        ModelMapper mapper = new ModelMapper();

        Accommodation accommodationEntity = this.accommodationRepository.findById(id).orElse(null);

        AccommodationServiceModel result = mapper.map(accommodationEntity, AccommodationServiceModel.class);

        result.setAccommodationTown(accommodationEntity.getAccommodationTown());
        result.setAccommodationComments(accommodationEntity.getAccommodationComments());

        return result;
    }

    @Override
    public void createAccommodation(AccommodationBindingModel accommodation) {
        ModelMapper modelMapper = new ModelMapper();

        Accommodation accommodationEntity = modelMapper.map(accommodation, Accommodation.class);

        accommodationEntity.setAccommodationUser(this.userRepository.findFirstByUsername(accommodation.getAccommodationUser()));
        accommodationEntity.setAccommodationTown(this.townRepository.findByTitle(accommodation.getAccommodationTown()));

        this.accommodationRepository.save(accommodationEntity);
    }

    @Override
    public void editAccommodation(Long id, AccommodationBindingModel accommodation) {
        ModelMapper modelMapper = new ModelMapper();

        Accommodation accommodationEntity = this.accommodationRepository
                .findById(id)
                .orElse(null);

        if(accommodationEntity == null) return;

        modelMapper.map(accommodation, accommodationEntity);

        this.accommodationRepository.save(accommodationEntity);
    }

    @Override
    public void deleteAccommodation(Long id) {
        if(this.accommodationRepository.findById(id).orElse(null) != null) {
            this.accommodationRepository.deleteById(id);
        }
    }

    @Override
    public String getMap() {
        return null;
    }

    @Override
    public List<AccommodationViewModel> fromServiceToViewModel() {
        List<AccommodationServiceModel> serviceModels = this.getAllAccommodations();
        List<AccommodationViewModel> viewModel = new ArrayList<>();

        for (AccommodationServiceModel serviceModel : serviceModels) {
            AccommodationViewModel currentViewModel = this.mapper.map(serviceModel, AccommodationViewModel.class);
            currentViewModel.setUser(serviceModel.getAccommodationUser().getUsername());
            List<String> comments = new ArrayList<>();
            for (Comment comment : serviceModel.getAccommodationComments()) {
                comments.add(comment.getDescription());
            }
            currentViewModel.setComments(comments);
            currentViewModel.setTown(serviceModel.getAccommodationTown().getTitle());

            viewModel.add(currentViewModel);
        }
        return viewModel;
    }
}
