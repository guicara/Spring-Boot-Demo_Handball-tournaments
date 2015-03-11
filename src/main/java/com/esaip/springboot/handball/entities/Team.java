package com.esaip.springboot.handball.entities;

import javax.persistence.*;

/**
 * An entity class which contains the information of a single team.
 *
 * @author Guillaume MOREL-BAILLY
 */
@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 255, nullable = false, unique = true)
    private String name;

    @Column(name = "description", length = 1000, nullable = true)
    private String description;

    @Column(name = "path_logo", nullable = true)
    private String pathLogo;

    public Team() {
    }

    public Team(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Team(String name, String description, String pathLogo) {
        this.name = name;
        this.description = description;
        this.pathLogo = pathLogo;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPathLogo() {
        return pathLogo;
    }

    public void setPathLogo(String pathLogo) {
        this.pathLogo = pathLogo;
    }

}
