package org.softuni.accommodationreviews.services;

import org.softuni.accommodationreviews.entities.Tourist;
import org.softuni.accommodationreviews.models.view.TouristRegisterRequestModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface TouristService extends UserDetailsService {

    Tourist register(TouristRegisterRequestModel model);

}
