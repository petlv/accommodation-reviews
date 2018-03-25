package org.softuni.accommodationreviews.entities.enums;

public enum TownName {
    BALCHIK,
    BANSKO,
    BELOGRADCHIK,
    BURGAS,
    DEVIN,
    HISARYA,
    KALOFER,
    KOTEL,
    PLOVDIV,
    SOFIA,
    SOZOPOL,
    SUNNY_BEACH,
    VARNA,
    ZHERAVNA,
    ZLATOGRAD;

    public TownName parseFromString(String town) {
        return TownName.valueOf(town.toUpperCase());
    }

}
