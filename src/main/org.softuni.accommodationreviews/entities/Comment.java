package org.softuni.accommodationreviews.entities;

import org.hibernate.annotations.GenericGenerator;

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
    @JoinColumn(name = "tourist_comments")
    private Tourist commentTourist;

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

    public Tourist getCommentTourist() {
        return commentTourist;
    }

    public void setCommentTourist(Tourist commentTourist) {
        this.commentTourist = commentTourist;
    }

    public Accommodation getCommentAccommodation() {
        return commentAccommodation;
    }

    public void setCommentAccommodation(Accommodation commentAccommodation) {
        this.commentAccommodation = commentAccommodation;
    }
}
