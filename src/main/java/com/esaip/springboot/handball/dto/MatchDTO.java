package com.esaip.springboot.handball.dto;

import com.esaip.springboot.handball.entities.Season;
import com.esaip.springboot.handball.entities.Team;

import java.util.Date;

/**
 * A DTO object which is used as a form object in create match and edit match form
 *
 * @author Guillaume MOREL-BAILLY
 */
public class MatchDTO {

    private Long id;

    private Integer scoreHome;

    private Integer scoreAway;

    private Integer oldScoreHome;

    private Integer oldScoreAway;

    private Date playedAt;

    private Team teamHome;

    private Team teamAway;

    private Season season;

    public MatchDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getScoreHome() {
        return scoreHome;
    }

    public void setScoreHome(Integer scoreHome) {
        this.scoreHome = scoreHome;
    }

    public Integer getScoreAway() {
        return scoreAway;
    }

    public void setScoreAway(Integer scoreAway) {
        this.scoreAway = scoreAway;
    }

    public Integer getOldScoreHome() {
        return oldScoreHome;
    }

    public void setOldScoreHome(Integer oldScoreHome) {
        this.oldScoreHome = oldScoreHome;
    }

    public Integer getOldScoreAway() {
        return oldScoreAway;
    }

    public void setOldScoreAway(Integer oldScoreAway) {
        this.oldScoreAway = oldScoreAway;
    }

    public Date getPlayedAt() {
        return playedAt;
    }

    public void setPlayedAt(Date playedAt) {
        this.playedAt = playedAt;
    }

    public Team getTeamHome() {
        return teamHome;
    }

    public void setTeamHome(Team teamHome) {
        this.teamHome = teamHome;
    }

    public Team getTeamAway() {
        return teamAway;
    }

    public void setTeamAway(Team teamAway) {
        this.teamAway = teamAway;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

}
