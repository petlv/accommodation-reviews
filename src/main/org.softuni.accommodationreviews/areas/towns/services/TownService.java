package org.softuni.accommodationreviews.areas.towns.services;

import org.softuni.accommodationreviews.areas.towns.Town;
import org.softuni.accommodationreviews.areas.towns.models.TownBindingModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TownService {

    List<TownBindingModel> getAllTowns();

    TownBindingModel getByName(String name);

    Town addTown(TownBindingModel townModel);


}
