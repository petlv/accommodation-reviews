package org.softuni.accommodationreviews.areas.comments.models;

public class CommentViewModel {

    private Long id;

    private String description;

    private String commentUser;

    private String commentAccommodation;

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

    public String getCommentUser() {
        return commentUser;
    }

    public void setCommentUser(String commentUser) {
        this.commentUser = commentUser;
    }

    public String getCommentAccommodation() {
        return commentAccommodation;
    }

    public void setCommentAccommodation(String commentAccommodation) {
        this.commentAccommodation = commentAccommodation;
    }
}
