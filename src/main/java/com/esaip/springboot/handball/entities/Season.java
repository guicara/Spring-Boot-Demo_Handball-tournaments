package com.esaip.springboot.handball.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * An entity class which contains the information of a single season.
 *
 * @author Guillaume MOREL-BAILLY
 */
@Entity
@Table(name = "seasons")
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 255, nullable = false, unique = true)
    private String name;

    @Column(name = "start_at", nullable = true)
    private Date startAt;

    @Column(name = "end_at", nullable = true)
    private Date endAt;

    public Season() {
    }

    public Season(String name, Date startAt, Date endAt) {
        this.name = name;
        this.startAt = startAt;
        this.endAt = endAt;
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

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    @Override
    public String toString() {
        return "Season [id=" + id
                + ", name=" + name
                + ", startAt=" + startAt
                + ", endAt=" + endAt
                + "]";
    }

}
