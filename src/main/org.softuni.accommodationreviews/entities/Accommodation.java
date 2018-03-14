package org.softuni.accommodationreviews.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "accommodations")
public class Accommodation {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(name = "owner", nullable = false)
    @OneToOne(cascade = CascadeType.ALL)
    private Owner accommodationOwner;

    @Column(nullable = false)
    private LocalDate validUntil;

    @OneToMany(targetEntity = Comment.class)
    private Set<Comment> comments;


    public Accommodation() {
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Owner getAccommodationOwner() {
        return accommodationOwner;
    }

    public void setAccommodationOwner(Owner accommodationOwner) {
        this.accommodationOwner = accommodationOwner;
    }

    public LocalDate getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDate validUntil) {
        this.validUntil = validUntil;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
