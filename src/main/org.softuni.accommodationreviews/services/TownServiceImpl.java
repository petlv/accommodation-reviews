package org.softuni.accommodationreviews.services;


import org.modelmapper.ModelMapper;
import org.softuni.accommodationreviews.entities.Town;
import org.softuni.accommodationreviews.models.binding.TownBindingModel;
import org.softuni.accommodationreviews.repositories.TownRepository;
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
    public Town addTown(TownBindingModel townModel) {
        Town town = this.mapper.map(townModel, Town.class);
        return this.townRepository.save(town);
    }

    @Override
    public List<TownBindingModel> getAllTowns() {
        ModelMapper modelMapper = new ModelMapper();

        return this.townRepository
                .findAll()
                .stream()
                .map(x -> modelMapper.map(x, TownBindingModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public TownBindingModel getByName(String name) {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(this.townRepository.findByTitle(name), TownBindingModel.class);
    }
}
