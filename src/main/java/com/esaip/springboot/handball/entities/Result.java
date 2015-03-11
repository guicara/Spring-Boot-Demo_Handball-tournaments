package com.esaip.springboot.handball.entities;

import javax.persistence.*;

/**
 * An entity class which contains the information of a single result.
 *
 * @author Guillaume MOREL-BAILLY
 */
@Entity
@Table(name = "results")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private Integer played;

    private Integer win;

    private Integer draft;

    private Integer loss;

    @ManyToOne
    @JoinColumn(name ="id_season")
    private Season season;

    @ManyToOne
    @JoinColumn(name ="id_team")
    private Team team;

    public Result() {
    }

    public Result(Integer played, Integer win, Integer draft, Integer loss, Season season, Team team) {
        this.played = played;
        this.win = win;
        this.draft = draft;
        this.loss = loss;
        this.season = season;
        this.team = team;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPlayed() {
        return played;
    }

    public void setPlayed(Integer played) {
        this.played = played;
    }

    public Integer getWin() {
        return win;
    }

    public void setWin(Integer win) {
        this.win = win;
    }

    public Integer getDraft() {
        return draft;
    }

    public void setDraft(Integer draft) {
        this.draft = draft;
    }

    public Integer getLoss() {
        return loss;
    }

    public void setLoss(Integer loss) {
        this.loss = loss;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

}
