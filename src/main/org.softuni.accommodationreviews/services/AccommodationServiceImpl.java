package org.softuni.accommodationreviews.services;

import org.softuni.accommodationreviews.models.service.AccommodationServiceModel;
import org.softuni.accommodationreviews.repositories.AccommodationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    private final AccommodationsRepository accommodationsRepository;

    @Autowired
    public AccommodationServiceImpl(AccommodationsRepository accommodationsRepository) {
        this.accommodationsRepository = accommodationsRepository;
    }

    @Override
    public List<AccommodationServiceModel> getAllListings() {
        ModelMapper modelMapper = new ModelMapper();

        return this.accommodationsRepository
                .findAll()
                .stream()
                .map(x -> modelMapper.map(x, AccommodationServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AccommodationServiceModel> getAccommodationsBySimilarName(String name) {
        ModelMapper modelMapper = new ModelMapper();

        return this.accommodationsRepository
                .findAll()
                .stream()
                .filter(x -> x.getName().contains(name))
                .map(x -> modelMapper.map(x, AccommodationServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public AccommodationServiceModel getByName(String name) {
        return null;
    }
}
