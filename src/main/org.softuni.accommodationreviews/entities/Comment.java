package org.softuni.accommodationreviews.entities;

import org.hibernate.annotations.GenericGenerator;
import org.softuni.accommodationreviews.areas.accommodations.Accommodation;
import org.softuni.accommodationreviews.areas.users.User;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_comments")
    private User commentUser;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accommodation_comments")
    private Accommodation commentAccommodation;

    public Comment() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
