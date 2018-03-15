package org.softuni.accommodationreviews.entities;

import org.hibernate.annotations.GenericGenerator;
import org.softuni.accommodationreviews.entities.enums.TownName;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "towns")
public class Town {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private TownName name;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accommodationTown")
    private Set<Accommodation> townAccommodations;

    public Town() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TownName getName() {
        return name;
    }

    public void setName(TownName name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Set<Accommodation> getTownAccommodations() {
        return townAccommodations;
    }

    public void setTownAccommodations(Set<Accommodation> townAccommodations) {
        this.townAccommodations = townAccommodations;
    }
}
