package org.softuni.accommodationreviews.areas.users.services;

import org.softuni.accommodationreviews.areas.users.User;
import org.softuni.accommodationreviews.areas.users.UserBindingModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void checkIfOwner(UserBindingModel userModel, String optionsRadios, User user);

    void assignRole(User user, String optionsRadios);

    User register(UserBindingModel model, String optionsRadios);



}