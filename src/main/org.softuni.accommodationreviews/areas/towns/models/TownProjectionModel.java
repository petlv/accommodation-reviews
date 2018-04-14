package org.softuni.accommodationreviews.areas.towns.models;

import org.softuni.accommodationreviews.areas.towns.Town;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = Town.class, name = "townServiceModel")
public interface TownProjectionModel {

    String getTitle();

    Double getLatitude();

    Double getLongitude();

}
