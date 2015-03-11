package com.esaip.springboot.handball.services;

import com.esaip.springboot.handball.dto.ResultDTO;
import com.esaip.springboot.handball.entities.Result;
import com.esaip.springboot.handball.services.exceptions.ResultNotFoundException;

import java.util.List;

/**
 * Declares methods used to obtain and modify result information
 *
 * @author Guillaume MOREL-BAILLY
 */
public interface ResultService {

    /**
     * Finds all results
     *
     * @return A list of results
     */
    public List<Result> getAll();

    /**
     * Finds result by ID
     *
     * @param id The ID of the wanted result
     * @return The found result. If no result is found, this method returns null.
     */
    public Result get(Long id);

    /**
     * Creates a new result
     *
     * @param resultForm The information of the created result
     * @return The created result
     */
    public Result create(ResultDTO resultForm);

    /**
     * Updates the information of a result
     *
     * @param resultForm The information of the updated result
     * @return The updated result
     * @throws ResultNotFoundException If no result is found with given ID
     */
    public Result update(ResultDTO resultForm) throws ResultNotFoundException;

    /**
     * Deletes a result
     *
     * @param id The ID of the deleted result
     * @throws ResultNotFoundException If no result is found with the given ID
     */
    public void delete(Long id) throws ResultNotFoundException;

}
