package org.softuni.accommodationreviews.areas.accommodations.services;

import org.softuni.accommodationreviews.areas.accommodations.models.AccommodationBindingModel;
import org.softuni.accommodationreviews.areas.accommodations.models.AccommodationServiceModel;
import org.softuni.accommodationreviews.areas.accommodations.models.AccommodationViewModel;

import java.util.List;

public interface AccommodationService {

    List<AccommodationServiceModel> getAllAccommodations();

    AccommodationServiceModel getById(Long id);

    void createAccommodation(AccommodationBindingModel accommodation);

    void editAccommodation(Long id, AccommodationBindingModel accommodation);

    void deleteAccommodation(Long id);

    String getMap();

    List<AccommodationViewModel> fromServiceToViewModel();

}
