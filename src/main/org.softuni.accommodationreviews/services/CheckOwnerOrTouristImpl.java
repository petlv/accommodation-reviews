package org.softuni.accommodationreviews.services;

import org.softuni.accommodationreviews.models.view.OwnerRegisterRequestModel;
import org.softuni.accommodationreviews.models.view.TouristRegisterRequestModel;
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
    public void register(TouristRegisterRequestModel touristModel, OwnerRegisterRequestModel ownerModel,
                         String optionsRadios) {

        if (optionsRadios.equals("tourist")) {
            this.touristService.register(touristModel);
        } else if (optionsRadios.equals("owner")) {
            this.ownerService.register(ownerModel);
        }
    }

    @Override
    public void login() {

    }
}
