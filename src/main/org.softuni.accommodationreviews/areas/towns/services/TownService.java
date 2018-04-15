package org.softuni.accommodationreviews.areas.towns.services;

import org.softuni.accommodationreviews.areas.towns.models.TownBindingModel;
import org.softuni.accommodationreviews.areas.towns.models.TownServiceModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TownService {

    void addTown(TownBindingModel townModel);

    List<TownServiceModel> getAllTowns();

    List<TownServiceModel> getTownsBySimilarName(String name);

    TownServiceModel getByName(String name);

}
