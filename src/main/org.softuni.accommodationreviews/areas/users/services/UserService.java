package org.softuni.accommodationreviews.areas.users.services;

import org.softuni.accommodationreviews.areas.users.User;
import org.softuni.accommodationreviews.areas.users.models.UserBindingModel;
import org.softuni.accommodationreviews.areas.users.models.UserServiceModel;
import org.softuni.accommodationreviews.areas.users.models.UserViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserServiceModel findByIdCustom(Long id);

    UserServiceModel findByUsernameCustom(String username);

    void checkIfOwner(UserBindingModel userModel, String optionsRadios, User user);

    void assignRole(User user, String optionsRadios);

    boolean register(UserBindingModel model, String optionsRadios);

    boolean edit(UserViewModel model, String username);



}
