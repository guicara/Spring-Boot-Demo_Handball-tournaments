package com.esaip.springboot.handball.services.impl;

import com.esaip.springboot.handball.dto.MatchDTO;
import com.esaip.springboot.handball.entities.Match;
import com.esaip.springboot.handball.repositories.MatchRepository;
import com.esaip.springboot.handball.services.MatchService;
import com.esaip.springboot.handball.services.ResultService;
import com.esaip.springboot.handball.services.exceptions.MatchNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Match Service
 *
 * This implementation of the MatchService interface communicates with
 * the database by using a Spring Data JPA repository.
 *
 * @author Guillaume MOREL-BAILLY
 */
@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private ResultService resultService;

    /**
     * Finds all matchs
     *
     * @return A list of matchs
     */
    @Transactional(readOnly = true)
    public List<Match> getAll() {
        return matchRepository.findAll();
    }

    /**
     * Finds match by ID
     *
     * @param id The ID of the wanted match
     * @return The found match. If no match is found, this method returns null.
     */
    @Transactional(readOnly = true)
    public Match get(Long id) {
        return matchRepository.findOne(id);
    }

    /**
     * Creates a new match
     *
     * @param matchForm The information of the created match
     * @return The created match
     */
    @Transactional(readOnly = false)
    public Match create(MatchDTO matchForm) {
        Match match = new Match(matchForm.getScoreHome(), matchForm.getScoreAway(), matchForm.getPlayedAt(), matchForm.getTeamHome(), matchForm.getTeamAway(), matchForm.getSeason());

        matchRepository.save(match);

        // Calculate the points for both teams if the scores were filled
        if (match.getScoreHome() != null && match.getScoreAway() != null) {
            resultService.calculate(match, false);
        }

        return match;
    }

    /**
     * Updates the information of a match
     *
     * @param matchForm The information of the updated match
     * @return The updated match
     * @throws MatchNotFoundException If no match is found with given ID
     */
    @Transactional(readOnly = false, rollbackFor = MatchNotFoundException.class)
    public Match update(MatchDTO matchForm) throws MatchNotFoundException {
        Match match = matchRepository.findOne(matchForm.getId());

        if (match == null) {
            throw new MatchNotFoundException("No match found with id = " + matchForm.getId());
        }

        match.setPlayedAt(matchForm.getPlayedAt());
        match.setSeason(matchForm.getSeason());

        match.setTeamHome(matchForm.getTeamHome());
        match.setTeamAway(matchForm.getTeamAway());

        // Recalculate the points for both teams if the scores were changed
        if (matchForm.getOldScoreAway() != matchForm.getScoreAway() || matchForm.getOldScoreHome() != matchForm.getScoreHome()) {
            resultService.calculate(match, true);
        }

        match.setScoreHome(matchForm.getScoreHome());
        match.setScoreAway(matchForm.getScoreAway());

        return matchRepository.save(match);
    }

    /**
     * Deletes a match
     *
     * @param id The ID of the deleted match
     * @throws MatchNotFoundException If no match is found with the given ID
     */
    @Transactional(readOnly = false, rollbackFor = MatchNotFoundException.class)
    public void delete(Long id) throws MatchNotFoundException {
        Match match = matchRepository.findOne(id);

        if (match == null) {
            throw new MatchNotFoundException("No match found with id = " + id);
        }

        matchRepository.delete(id);
    }

}
