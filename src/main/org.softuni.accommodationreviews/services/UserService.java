package org.softuni.accommodationreviews.services;

import org.softuni.accommodationreviews.entities.User;
import org.softuni.accommodationreviews.models.binding.UserBindingModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void checkIfOwner(UserBindingModel userModel, String optionsRadios, User user);

    void assignRole(User user, String optionsRadios);

    User register(UserBindingModel model, String optionsRadios);



}
