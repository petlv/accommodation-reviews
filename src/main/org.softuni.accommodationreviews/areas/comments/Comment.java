package org.softuni.accommodationreviews.areas.comments;

import org.softuni.accommodationreviews.areas.accommodations.Accommodation;
import org.softuni.accommodationreviews.areas.users.User;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*@GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )*/
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_comments")
    private User commentUser;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accommodation_comments")
    private Accommodation commentAccommodation;

    public Comment() {

    }

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
