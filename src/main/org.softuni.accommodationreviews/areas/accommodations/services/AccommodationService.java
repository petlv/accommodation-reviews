package org.softuni.accommodationreviews.areas.accommodations.services;

import org.softuni.accommodationreviews.areas.accommodations.models.AccommodationBindingModel;

public interface AccommodationService {

    List<VirusServiceModel> getAllAccommodations();

    VirusServiceModel getById(String id);

    void createAccommodation(AccommodationBindingModel accommodation);

    void editAccommodation(String id, AccommodationBindingModel accommodation);

    void deleteAccommodation(String id);

    String getMap();

}
