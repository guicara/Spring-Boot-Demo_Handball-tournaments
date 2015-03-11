package com.esaip.springboot.handball.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * An entity class which contains the information of a single match.
 *
 * @author Guillaume MOREL-BAILLY
 */
@Entity
@Table(name = "matchs")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "score_dom")
    private Integer scoreDom;

    @Column(name = "score_ext")
    private Integer scoreExt;

    @Column(name = "played_at", nullable = true)
    private Date playedAt;

    @ManyToOne
    @JoinColumn(name ="id_team_dom")
    private Team idTeamDom;

    @ManyToOne
    @JoinColumn(name ="id_team_ext")
    private Team idTeamExt;

    public Match() {
    }

    public Match(Integer scoreDom, Integer scoreExt, Date playedAt, Team idTeamDom, Team idTeamExt) {
        this.scoreDom = scoreDom;
        this.scoreExt = scoreExt;
        this.playedAt = playedAt;
        this.idTeamDom = idTeamDom;
        this.idTeamExt = idTeamExt;
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
