package org.softuni.accommodationreviews.areas.accommodations.models;

import java.util.ArrayList;
import java.util.List;

public class AddAccommodationViewModel {

    Long id;

    private List<String> towns;

    private List<String> comments;

    public AddAccommodationViewModel() {
        this.comments = new ArrayList<String>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public List<String> getTowns() {
        return towns;
    }

    public void setTowns(List<String> towns) {
        this.towns = towns;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }
}
