package com.esaip.springboot.handball.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * An entity class which contains the information of a single match comment.
 *
 * @author Guillaume MOREL-BAILLY
 */
@Entity
@Table(name = "match_comments")
public class MatchComment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "comment", length = 1000, nullable = true)
    private String comment;

    @Column(name = "posted_at", nullable = true)
    private Date postedAt;

    /**
     * Author of the comment
     */
    @ManyToOne
    @JoinColumn(name ="id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name ="id_match")
    private Match match;

    public MatchComment() {
    }

    public MatchComment(String comment, Date postedAt, User user, Match match) {
        this.comment = comment;
        this.postedAt = postedAt;
        this.user = user;
        this.match = match;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(Date postedAt) {
        this.postedAt = postedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

}
