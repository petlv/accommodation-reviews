package org.softuni.accommodationreviews.services;


import org.modelmapper.ModelMapper;
import org.softuni.accommodationreviews.models.service.TownServiceModel;
import org.softuni.accommodationreviews.repositories.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;

    @Autowired
    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;
    }

    @Override
    public List<TownServiceModel> getAllTowns() {
        ModelMapper modelMapper = new ModelMapper();

        return this.townRepository
                .findAll()
                .stream()
                .map(x -> modelMapper.map(x, TownServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public TownServiceModel getByName(String name) {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(this.townRepository.findByName(name), TownServiceModel.class);
    }
}
