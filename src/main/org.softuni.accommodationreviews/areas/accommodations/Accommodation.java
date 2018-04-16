package org.softuni.accommodationreviews.areas.accommodations;

import org.softuni.accommodationreviews.areas.comments.Comment;
import org.softuni.accommodationreviews.areas.towns.Town;
import org.softuni.accommodationreviews.areas.users.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "accommodations")
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*@GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )*/
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column
    private LocalDate validUntil;

    private String photo;

    @ManyToOne
    @JoinColumn(name = "user_accommodations")
    private User accommodationUser;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commentAccommodation")
    private Set<Comment> accommodationComments;

    @ManyToOne
    @JoinColumn(name = "town_accommodations")
    private Town accommodationTown;


    public Accommodation() {
        this.accommodationComments = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public LocalDate getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDate validUntil) {
        this.validUntil = validUntil;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public User getAccommodationUser() {
        return accommodationUser;
    }

    public void setAccommodationUser(User accommodationUser) {
        this.accommodationUser = accommodationUser;
    }

    public Set<Comment> getAccommodationComments() {
        return accommodationComments;
    }

    public void setAccommodationComments(Set<Comment> accommodationComments) {
        this.accommodationComments = accommodationComments;
    }

    public Town getAccommodationTown() {
        return accommodationTown;
    }

    public void setAccommodationTown(Town accommodationTown) {
        this.accommodationTown = accommodationTown;
    }
}
