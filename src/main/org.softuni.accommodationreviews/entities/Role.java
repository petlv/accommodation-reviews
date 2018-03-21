package org.softuni.accommodationreviews.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Role {

    private String id;

    private String name;

    private Set<Owner> owners;

    private Set<Tourist> tourists;

    public Role() {
        this.owners = new HashSet<>();
        this.tourists = new HashSet<>();
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    public Set<Owner> getOwners() {
        return owners;
    }

    public void setOwners(Set<Owner> owners) {
        this.owners = owners;
    }

    public Set<Tourist> getTourists() {
        return tourists;
    }

    public void setTourists(Set<Tourist> tourists) {
        this.tourists = tourists;
    }
}
