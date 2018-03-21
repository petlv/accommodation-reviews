package org.softuni.accommodationreviews.services;

import org.softuni.accommodationreviews.entities.Owner;
import org.softuni.accommodationreviews.models.view.OwnerRegisterRequestModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface OwnerService extends UserDetailsService {

    Owner register(OwnerRegisterRequestModel model);

}
