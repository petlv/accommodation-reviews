package org.softuni.accommodationreviews.services;

import org.softuni.accommodationreviews.entities.Owner;
import org.softuni.accommodationreviews.models.binding.UserBindingModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface OwnerService extends UserDetailsService {

    Owner register(UserBindingModel userModel);

}
