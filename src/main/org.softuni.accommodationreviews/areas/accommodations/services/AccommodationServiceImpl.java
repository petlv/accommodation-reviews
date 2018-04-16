package org.softuni.accommodationreviews.areas.accommodations.services;

import org.modelmapper.ModelMapper;
import org.softuni.accommodationreviews.areas.accommodations.Accommodation;
import org.softuni.accommodationreviews.areas.accommodations.AccommodationRepository;
import org.softuni.accommodationreviews.areas.accommodations.models.AccommodationBindingModel;
import org.softuni.accommodationreviews.areas.accommodations.models.AccommodationServiceModel;
import org.softuni.accommodationreviews.areas.towns.TownRepository;
import org.softuni.accommodationreviews.areas.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccommodationServiceImpl implements AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final TownRepository townRepository;
    private final UserRepository userRepository;

    @Autowired
    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, TownRepository townRepository,
                                    UserRepository userRepository) {
        this.accommodationRepository = accommodationRepository;
        this.townRepository = townRepository;
        this.userRepository = userRepository;
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
        result.setAccommodationComments(accommodationEntity.getAccommodationComments()
                .stream().map(x -> x.getId()).collect(Collectors.toSet()));

        return result;
    }

    @Override
    public void createAccommodation(AccommodationBindingModel accommodation) {
        ModelMapper modelMapper = new ModelMapper();

        Accommodation accommodationEntity = modelMapper.map(accommodation, Accommodation.class);

        accommodationEntity.setAccommodationUser(this.userRepository.findFirstByUsername(accommodation.getAccommodationUser()));
        accommodationEntity.setAccommodationTown(this.townRepository.findByTitle(accommodation.getAccommodationTown()));

        accommodationEntity.setAccommodationComments(new HashSet<>());

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
}
