package com.esaip.springboot.handball.entities;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    @Column(name = "played_at", nullable = true)
    private Date playedAt;

    /**
     * Referring to the team who plays games at their venue.
     */
    @ManyToOne
    @JoinColumn(name = "id_team_home")
    private Team teamHome;

    /**
     * Referring to the team who plays games elsewhere.
     * When a team is not the host, it must travel to games.
     */
    @ManyToOne
    @JoinColumn(name = "id_team_away")
    private Team teamAway;

    /**
     * Score of the home team
     */
    @Column(name = "score_home")
    private Integer scoreHome;

    /**
     * Score of the road/away team
     */
    @Column(name = "score_away")
    private Integer scoreAway;

    @ManyToOne
    @JoinColumn(name = "id_season")
    private Season season;

    /**
     * User comments about the match
     */
    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
    private Set<MatchComment> comments = new HashSet<MatchComment>();

    public Match() {
    }

    public Match(Integer scoreHome, Integer scoreAway, Date playedAt, Team teamHome, Team teamAway, Season season) {
        this.scoreHome = scoreHome;
        this.scoreAway = scoreAway;
        this.playedAt = playedAt;
        this.teamHome = teamHome;
        this.teamAway = teamAway;
        this.season = season;
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

    public Date getPlayedAt() {
        return playedAt;
    }

    public void setPlayedAt(Date playedAt) {
        this.playedAt = playedAt;
    }

    public String getPlayedAtFormatted() {
        // French date format
        // TODO : use localization
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE d MMMM yyyy");

        return dateFormat.format(playedAt);
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

    public Set<MatchComment> getComments() {
        return comments;
    }

    public void addComment(MatchComment userComment) {
        userComment.setMatch(this);
        comments.add(userComment);
    }

}
