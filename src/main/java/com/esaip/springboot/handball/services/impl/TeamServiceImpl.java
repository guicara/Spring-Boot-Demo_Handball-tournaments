package com.esaip.springboot.handball.services.impl;

import com.esaip.springboot.handball.dto.TeamDTO;
import com.esaip.springboot.handball.entities.Team;
import com.esaip.springboot.handball.repositories.TeamRepository;
import com.esaip.springboot.handball.services.TeamService;
import com.esaip.springboot.handball.services.exceptions.TeamNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Team Service
 *
 * This implementation of the TeamService interface communicates with
 * the database by using a Spring Data JPA repository.
 *
 * @author Guillaume MOREL-BAILLY
 */
@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    /**
     * Finds all teams
     *
     * @return A list of teams
     */
    @Transactional(readOnly = true)
    public List<Team> getAll() {
        return teamRepository.findAll();
    }

    /**
     * Finds team by ID
     *
     * @param id The ID of the wanted team
     * @return The found team. If no team is found, this method returns null.
     */
    @Transactional(readOnly = true)
    public Team get(Long id) {
        return teamRepository.findOne(id);
    }

    /**
     * Creates a new team
     *
     * @param teamForm The information of the created team
     * @return The created team
     */
    @Transactional(readOnly = false)
    public Team create(TeamDTO teamForm) {
        Team team = new Team(teamForm.getName(), teamForm.getDescription(), teamForm.getPathLogo());

        return teamRepository.save(team);
    }

    /**
     * Updates the information of a team
     *
     * @param teamForm The information of the updated team
     * @return The updated team
     * @throws TeamNotFoundException If no team is found with given ID
     */
    @Transactional(readOnly = false, rollbackFor = TeamNotFoundException.class)
    public Team update(TeamDTO teamForm) throws TeamNotFoundException {
        Team team = teamRepository.findOne(teamForm.getId());

        if (team == null) {
            throw new TeamNotFoundException("No team found with id = " + teamForm.getId());
        }

        team.setName(teamForm.getName());
        team.setDescription(teamForm.getDescription());
        team.setPathLogo(teamForm.getPathLogo());

        return teamRepository.save(team);
    }

    /**
     * Deletes a team
     *
     * @param id The ID of the deleted team
     * @throws TeamNotFoundException If no team is found with the given ID
     */
    @Transactional(readOnly = false, rollbackFor = TeamNotFoundException.class)
    public void delete(Long id) throws TeamNotFoundException {
        Team team = teamRepository.findOne(id);

        if (team == null) {
            throw new TeamNotFoundException("No team found with id = " + id);
        }

        teamRepository.delete(id);
    }

}
