package org.softuni.accommodationreviews.services;

import org.softuni.accommodationreviews.models.service.TownServiceModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TownService {

    List<TownServiceModel> getAllTowns();

    TownServiceModel getByName(String name);
}
