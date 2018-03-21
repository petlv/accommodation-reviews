package org.softuni.accommodationreviews.services;

import org.softuni.accommodationreviews.models.view.OwnerRegisterRequestModel;
import org.softuni.accommodationreviews.models.view.TouristRegisterRequestModel;

public interface CheckOwnerOrTourist {

    void register(TouristRegisterRequestModel touristModel, OwnerRegisterRequestModel ownerModel,
                  String optionsRadios);

    void login();

}
