package com.esaip.springboot.handball.dto;

import com.esaip.springboot.handball.entities.User;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * A DTO object which is used as a form object in create match's comment
 *
 * @author Guillaume MOREL-BAILLY
 */
public class MatchCommentDTO {

    private Long id;

    @NotNull
    @NotEmpty
    @Size(max = 1000)
    private String comment;

    private Date postedAt;

    private User user;

    public MatchCommentDTO() {
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

}
