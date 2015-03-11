package com.esaip.springboot.handball.services;

import com.esaip.springboot.handball.dto.MatchDTO;
import com.esaip.springboot.handball.entities.Match;
import com.esaip.springboot.handball.services.exceptions.MatchNotFoundException;

import java.util.List;

/**
 * Declares methods used to obtain and modify match information
 *
 * @author Guillaume MOREL-BAILLY
 */
public interface MatchService {

    /**
     * Finds all matchs
     *
     * @return A list of matchs
     */
    public List<Match> getAll();

    /**
     * Finds match by ID
     *
     * @param id The ID of the wanted match
     * @return The found match. If no match is found, this method returns null.
     */
    public Match get(Long id);

    /**
     * Creates a new match
     *
     * @param matchForm The information of the created match
     * @return The created match
     */
    public Match create(MatchDTO matchForm);

    /**
     * Updates the information of a match
     *
     * @param matchForm The information of the updated match
     * @return The updated match
     * @throws MatchNotFoundException If no match is found with given ID
     */
    public Match update(MatchDTO matchForm) throws MatchNotFoundException;

    /**
     * Deletes a match
     *
     * @param id The ID of the deleted match
     * @throws MatchNotFoundException If no match is found with the given ID
     */
    public void delete(Long id) throws MatchNotFoundException;

}
