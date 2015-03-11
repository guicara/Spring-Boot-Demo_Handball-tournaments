package com.esaip.springboot.handball.services;

import com.esaip.springboot.handball.dto.TeamDTO;
import com.esaip.springboot.handball.entities.Team;
import com.esaip.springboot.handball.services.exceptions.TeamNotFoundException;

import java.util.List;

/**
 * Declares methods used to obtain and modify team information
 *
 * @author Guillaume MOREL-BAILLY
 */
public interface TeamService {

    /**
     * Finds all teams
     *
     * @return A list of teams
     */
    public List<Team> getAll();

    /**
     * Finds team by ID
     *
     * @param id The ID of the wanted team
     * @return The found team. If no team is found, this method returns null.
     */
    public Team get(Long id);

    /**
     * Creates a new team
     *
     * @param teamForm The information of the created team
     * @return The created team
     */
    public Team create(TeamDTO teamForm);

    /**
     * Updates the information of a team
     *
     * @param teamForm The information of the updated team
     * @return The updated team
     * @throws TeamNotFoundException If no team is found with given ID
     */
    public Team update(TeamDTO teamForm) throws TeamNotFoundException;

    /**
     * Deletes a team
     *
     * @param id The ID of the deleted team
     * @throws TeamNotFoundException If no team is found with the given ID
     */
    public void delete(Long id) throws TeamNotFoundException;

}
