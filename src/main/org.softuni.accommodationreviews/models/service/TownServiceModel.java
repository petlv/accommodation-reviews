package org.softuni.accommodationreviews.models.service;

import org.softuni.accommodationreviews.entities.Town;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = Town.class, name = "townServiceModel")
public interface TownServiceModel {

    String getTitle();

    Double getLatitude();

    Double getLongitude();

}
