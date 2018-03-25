package org.softuni.accommodationreviews.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tourists")
public class Tourist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    /*@GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )*/
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "commentTourist")
    private Set<Comment> touristComments;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> touristRoles;

    public Tourist() {
        this.touristRoles = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Comment> getTouristComments() {
        return touristComments;
    }

    public void setTouristComments(Set<Comment> touristComments) {
        this.touristComments = touristComments;
    }

    public Set<Role> getTouristRoles() {
        return touristRoles;
    }

    public void setTouristRoles(Set<Role> touristRoles) {
        this.touristRoles = touristRoles;
    }
}
