package org.softuni.accommodationreviews.areas.comments.models;

import org.softuni.accommodationreviews.areas.accommodations.Accommodation;
import org.softuni.accommodationreviews.areas.users.User;

public class CommentServiceModel {

    private Long id;

    private String description;

    private User commentUser;

    private Accommodation commentAccommodation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getCommentUser() {
        return commentUser;
    }

    public void setCommentUser(User commentUser) {
        this.commentUser = commentUser;
    }

    public Accommodation getCommentAccommodation() {
        return commentAccommodation;
    }

    public void setCommentAccommodation(Accommodation commentAccommodation) {
        this.commentAccommodation = commentAccommodation;
    }
}
