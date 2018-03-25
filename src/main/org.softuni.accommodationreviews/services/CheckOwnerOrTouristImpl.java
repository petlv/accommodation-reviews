package org.softuni.accommodationreviews.services;

import org.softuni.accommodationreviews.models.binding.UserBindingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckOwnerOrTouristImpl implements CheckOwnerOrTourist {

    private final TouristService touristService;
    private final OwnerService ownerService;

    @Autowired
    public CheckOwnerOrTouristImpl(TouristService touristService, OwnerService ownerService) {
        this.touristService = touristService;
        this.ownerService = ownerService;
    }

    @Override
    public void register(UserBindingModel userModel, String optionsRadios) {

        if (optionsRadios.equals("tourist")) {
            this.touristService.register(userModel);
        } else if (optionsRadios.equals("owner")) {
            this.ownerService.register(userModel);
        }
    }

    @Override
    public void login() {

    }
}
