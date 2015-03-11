package com.esaip.springboot.handball.services;

import com.esaip.springboot.handball.dto.SeasonDTO;
import com.esaip.springboot.handball.entities.Season;
import com.esaip.springboot.handball.services.exceptions.SeasonNotFoundException;

import java.util.List;

/**
 * Declares methods used to obtain and modify season information
 *
 * @author Guillaume MOREL-BAILLY
 */
public interface SeasonService {

    /**
     * Finds all seasons
     *
     * @return A list of seasons
     */
    public List<Season> getAll();

    /**
     * Finds season by ID
     *
     * @param id The ID of the wanted season
     * @return The found season. If no season is found, this method returns null.
     */
    public Season get(Long id);

    /**
     * Creates a new season
     *
     * @param seasonForm The information of the created season
     * @return The created season
     */
    public Season create(SeasonDTO seasonForm);

    /**
     * Updates the information of a season
     *
     * @param seasonForm The information of the updated season
     * @return The updated season
     * @throws SeasonNotFoundException If no season is found with given ID
     */
    public Season update(SeasonDTO seasonForm) throws SeasonNotFoundException;

    /**
     * Deletes a season
     *
     * @param id The ID of the deleted season
     * @throws SeasonNotFoundException If no season is found with the given ID
     */
    public void delete(Long id) throws SeasonNotFoundException;

}
