package org.softuni.accommodationreviews.areas.towns.services;


import org.modelmapper.ModelMapper;
import org.softuni.accommodationreviews.areas.towns.Town;
import org.softuni.accommodationreviews.areas.towns.TownRepository;
import org.softuni.accommodationreviews.areas.towns.models.TownBindingModel;
import org.softuni.accommodationreviews.areas.towns.models.TownServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;
    private final ModelMapper mapper;

    @Autowired
    public TownServiceImpl(TownRepository townRepository, ModelMapper mapper) {
        this.townRepository = townRepository;
        this.mapper = mapper;
    }

    @Override
    public void addTown(TownBindingModel townModel) {
        Town town = this.mapper.map(townModel, Town.class);
        this.townRepository.save(town);
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
    public List<TownServiceModel> getTownsBySimilarName(String name) {
        ModelMapper modelMapper = new ModelMapper();

        return this.townRepository
                .findAll()
                .stream()
                .filter(x -> x.getTitle().contains(name))
                .map(x -> modelMapper.map(x, TownServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public TownServiceModel getByName(String name) {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(this.townRepository.findByTitle(name), TownServiceModel.class);
    }
}