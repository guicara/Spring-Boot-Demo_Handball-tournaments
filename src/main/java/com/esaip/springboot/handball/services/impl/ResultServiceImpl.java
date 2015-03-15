package com.esaip.springboot.handball.services.impl;

import com.esaip.springboot.handball.dto.ResultDTO;
import com.esaip.springboot.handball.entities.Match;
import com.esaip.springboot.handball.entities.Result;
import com.esaip.springboot.handball.entities.Season;
import com.esaip.springboot.handball.entities.Team;
import com.esaip.springboot.handball.repositories.ResultRepository;
import com.esaip.springboot.handball.repositories.TeamRepository;
import com.esaip.springboot.handball.services.ResultService;
import com.esaip.springboot.handball.services.exceptions.ResultNotFoundException;
import com.esaip.springboot.handball.services.exceptions.TeamNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Result Service
 *
 * This implementation of the ResultService interface communicates with
 * the database by using a Spring Data JPA repository.
 *
 * @author Guillaume MOREL-BAILLY
 */
@Service
public class ResultServiceImpl implements ResultService {

    /**
     * Number of points to apply if the team won the match
     */
    public static final int SCORING_PTS_WIN = 2;

    /**
     * Number of points to apply if the team has a draw
     */
    public static final int SCORING_PTS_DRAFT = 1;

    /**
     * Number of points to apply if the team lost the match
     */
    public static final int SCORING_PTS_LOSS = 0;

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private TeamRepository teamRepository;

    /**
     * Finds all results
     *
     * @return A list of results
     */
    @Transactional(readOnly = true)
    public List<Result> getAll() {
        return resultRepository.findAll();
    }

    /**
     * Finds result by ID
     *
     * @param id The ID of the wanted result
     * @return The found result. If no result is found, this method returns null.
     */
    @Transactional(readOnly = true)
    public Result get(Long id) {
        return resultRepository.findOne(id);
    }

    /**
     * Creates a new result
     *
     * @param resultForm The information of the created result
     * @return The created result
     */
    @Transactional(readOnly = false)
    public Result create(ResultDTO resultForm) {
        Result result = new Result(resultForm.getPlayed(), resultForm.getWin(), resultForm.getDraft(), resultForm.getLoss(), resultForm.getSeason(), resultForm.getTeam());

        return resultRepository.save(result);
    }

    /**
     * Updates the information of a result
     *
     * @param resultForm The information of the updated result
     * @return The updated result
     * @throws ResultNotFoundException If no result is found with given ID
     */
    @Transactional(readOnly = false, rollbackFor = ResultNotFoundException.class)
    public Result update(ResultDTO resultForm) throws ResultNotFoundException {
        Result result = resultRepository.findOne(resultForm.getId());

        if (result == null) {
            throw new ResultNotFoundException("No entity result found with id = " + resultForm.getId());
        }

        result.setPlayed(resultForm.getPlayed());
        result.setWin(resultForm.getWin());
        result.setDraft(resultForm.getDraft());
        result.setLoss(resultForm.getLoss());
        result.setSeason(resultForm.getSeason());
        result.setTeam(resultForm.getTeam());
        
        return resultRepository.save(result);
    }

    /**
     * Deletes a result
     *
     * @param id The ID of the deleted result
     * @throws ResultNotFoundException If no result is found with the given ID
     */
    @Transactional(readOnly = false, rollbackFor = ResultNotFoundException.class)
    public void delete(Long id) throws ResultNotFoundException {
        Result result = resultRepository.findOne(id);

        if (result == null) {
            throw new ResultNotFoundException("No entity result found with id = " + id);
        }

        resultRepository.delete(id);
    }

    /**
     * Scoring for the Handball Tournament
     *
     * The tournament is based on a point system, where the points a team earn are determined by the score.
     * How are points calculated in this app?
     * - Win: 2 points
     * - Draft: 1 point
     * - Loss: 0 point
     *
     * @param match Instance of Match
     * @param updated TRUE if the result need to be updated
     */
    @Transactional(readOnly = false)
    public void calculate(Match match, boolean updated) {
        // IF the result need to be updated, decrement of 1, otherwise increment of 1
        int pts = (updated) ? -1 : 1;

        // Search for a result entry with this season and the home team

        Result resultHome = resultRepository.findBySeasonAndTeam(match.getSeason(), match.getTeamHome());
        if (resultHome == null) {
            resultHome = new Result(1, 0, 0, 0, match.getSeason(), match.getTeamHome());
        } else {
            // Increment the nb of played match
            resultHome.setPlayed(resultHome.getPlayed() + 1);
        }

        // Search for a result entry with this season and the away/road team

        Result resultAway = resultRepository.findBySeasonAndTeam(match.getSeason(), match.getTeamAway());
        if (resultAway == null) {
            resultAway = new Result(1, 0, 0, 0, match.getSeason(), match.getTeamAway());
        } else {
            // Increment the nb of played match
            resultAway.setPlayed(resultAway.getPlayed() + 1);
        }

        // Draft
        if (match.getScoreHome() == match.getScoreAway()) {
            resultHome.setDraft(resultHome.getDraft() + pts);
            resultAway.setDraft(resultAway.getDraft() + pts);
        }
        // Home team win / Away team loss
        else if (match.getScoreHome() > match.getScoreAway()) {
            resultHome.setWin(resultHome.getWin() + pts);
            resultAway.setLoss(resultAway.getLoss() + pts);
        }
        // Home team loss / Awat team win
        else {
            resultHome.setLoss(resultHome.getLoss() + pts);
            resultAway.setWin(resultAway.getWin() + pts);
        }
    }

}
