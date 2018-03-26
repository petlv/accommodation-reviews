package org.softuni.accommodationreviews.services;

import org.softuni.accommodationreviews.entities.Town;
import org.softuni.accommodationreviews.models.binding.TownBindingModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TownService {

    List<TownBindingModel> getAllTowns();

    TownBindingModel getByName(String name);

    Town addTown(TownBindingModel townModel);


}
