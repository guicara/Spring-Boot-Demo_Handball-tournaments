package com.esaip.springboot.handball.dto;

import com.esaip.springboot.handball.entities.Team;

import java.util.Date;

/**
 * A DTO object which is used as a form object in create match and edit match form
 *
 * @author Guillaume MOREL-BAILLY
 */
public class MatchDTO {

    private Long id;

    private Integer scoreDom;

    private Integer scoreExt;

    private Date playedAt;

    private Team idTeamDom;

    private Team idTeamExt;

    public MatchDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getScoreDom() {
        return scoreDom;
    }

    public void setScoreDom(Integer scoreDom) {
        this.scoreDom = scoreDom;
    }

    public Integer getScoreExt() {
        return scoreExt;
    }

    public void setScoreExt(Integer scoreExt) {
        this.scoreExt = scoreExt;
    }

    public Date getPlayedAt() {
        return playedAt;
    }

    public void setPlayedAt(Date playedAt) {
        this.playedAt = playedAt;
    }

    public Team getIdTeamDom() {
        return idTeamDom;
    }

    public void setIdTeamDom(Team idTeamDom) {
        this.idTeamDom = idTeamDom;
    }

    public Team getIdTeamExt() {
        return idTeamExt;
    }

    public void setIdTeamExt(Team idTeamExt) {
        this.idTeamExt = idTeamExt;
    }

}
