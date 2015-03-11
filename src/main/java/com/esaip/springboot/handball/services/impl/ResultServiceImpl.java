package com.esaip.springboot.handball.services.impl;

import com.esaip.springboot.handball.dto.ResultDTO;
import com.esaip.springboot.handball.entities.Result;
import com.esaip.springboot.handball.repositories.ResultRepository;
import com.esaip.springboot.handball.services.ResultService;
import com.esaip.springboot.handball.services.exceptions.ResultNotFoundException;
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

    @Autowired
    private ResultRepository resultRepository;

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

}
