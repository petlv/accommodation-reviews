package org.softuni.accommodationreviews.areas.accommodations.models;

import org.softuni.accommodationreviews.custom.FutureDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class AccommodationBindingModel {

    @NotEmpty(message = "Accommodation name cannot be empty.")
    @Size(min=3, max=15, message = "Accommodation name must be between 3 and 15 symbols long.")
    private String name;

    @NotEmpty(message = "Accommodation description cannot be empty.")
    @Size(min=5, max=100, message = "Accommodation description must be between 5 and 100 symbols long.")
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureDate(message = "Validity of offer has to be until a day in future.")
    private LocalDate validUntil;

    private String photo;

    private String accommodationUser;

    private String accommodationTown;

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

    public String getAccommodationUser() {
        return accommodationUser;
    }

    public void setAccommodationUser(String accommodationUser) {
        this.accommodationUser = accommodationUser;
    }

    public String getAccommodationTown() {
        return accommodationTown;
    }

    public void setAccommodationTown(String accommodationTown) {
        this.accommodationTown = accommodationTown;
    }
}
