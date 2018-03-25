package org.softuni.accommodationreviews.services;

import org.softuni.accommodationreviews.models.binding.UserBindingModel;

public interface CheckOwnerOrTourist {

    void register(UserBindingModel userModel, String optionsRadios);

    void login();

}
