package org.softuni.accommodationreviews.areas.accommodations.models;

import org.softuni.accommodationreviews.areas.towns.Town;
import org.softuni.accommodationreviews.areas.users.User;
import org.softuni.accommodationreviews.entities.Comment;

import java.time.LocalDate;
import java.util.Set;

public class AccommodationServiceModel {

    private Long id;

    private String name;

    private String description;

    private LocalDate validUntil;

    private String photo;

    private User accommodationUser;

    private Set<Comment> accommodationComments;

    private Town accommodationTown;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDate validUntil) {
        this.validUntil = validUntil;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public User getAccommodationUser() {
        return accommodationUser;
    }

    public void setAccommodationUser(User accommodationUser) {
        this.accommodationUser = accommodationUser;
    }

    public Set<Comment> getAccommodationComments() {
        return accommodationComments;
    }

    public void setAccommodationComments(Set<Comment> accommodationComments) {
        this.accommodationComments = accommodationComments;
    }

    public Town getAccommodationTown() {
        return accommodationTown;
    }

    public void setAccommodationTown(Town accommodationTown) {
        this.accommodationTown = accommodationTown;
    }
}
