package com.esaip.springboot.handball.dto;

import com.esaip.springboot.handball.entities.Season;
import com.esaip.springboot.handball.entities.Team;

/**
 * A DTO object which is used as a form object in create result and edit result form
 *
 * @author Guillaume MOREL-BAILLY
 */
public class ResultDTO {

    private Long id;

    private Integer played;

    private Integer win;

    private Integer draft;

    private Integer loss;

    private Season season;

    private Team team;

    public ResultDTO() {
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
