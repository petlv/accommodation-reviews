package org.softuni.accommodationreviews.areas.accommodations.models;

import java.util.ArrayList;
import java.util.List;

public class AddAccommodationViewModel {

    private List<String> towns;

    public AddAccommodationViewModel() {
        this.towns = new ArrayList<String>();
    }

    public List<String> getTowns() {
        return towns;
    }

    public void setTowns(List<String> towns) {
        this.towns = towns;
    }
}
