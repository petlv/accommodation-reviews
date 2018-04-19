package org.softuni.accommodationreviews.areas.users.models;

import org.softuni.accommodationreviews.areas.accommodations.Accommodation;
import org.softuni.accommodationreviews.areas.comments.Comment;
import org.softuni.accommodationreviews.areas.roles.Role;

import java.util.Set;

public class UserServiceModel {

    private Long id;

    private String username;

    private String password;

    private String fullName;

    private String email;

    private boolean isOwner;

    private Set<Accommodation> userAccommodations;

    private Set<Comment> userComments;

    private Set<Role> authorities;

    public UserServiceModel() {
    }

    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean getIsOwner() {
        return isOwner;
    }

    public void setIsOwner(boolean owner) {
        isOwner = owner;
    }

    public Set<Accommodation> getUserAccommodations() {
        return userAccommodations;
    }

    public void setUserAccommodations(Set<Accommodation> userAccommodations) {
        this.userAccommodations = userAccommodations;
    }

    public Set<Comment> getUserComments() {
        return userComments;
    }

    public void setUserComments(Set<Comment> userComments) {
        this.userComments = userComments;
    }

    public Set<Role> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }
}
