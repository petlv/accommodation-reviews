package org.softuni.accommodationreviews.areas.accommodations.services;

import org.softuni.accommodationreviews.areas.accommodations.models.AccommodationBindingModel;
import org.softuni.accommodationreviews.models.service.AccommodationServiceModel;

import java.util.List;

public interface AccommodationService {

    List<AccommodationServiceModel> getAllListings();

    List<AccommodationServiceModel> getAccommodationsBySimilarName(String name);

    AccommodationServiceModel getByName(String name);

    void createAccommodation(AccommodationBindingModel accommodationBindingModel);
}
