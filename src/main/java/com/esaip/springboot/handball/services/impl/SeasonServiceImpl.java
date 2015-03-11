package com.esaip.springboot.handball.services.impl;

import com.esaip.springboot.handball.dto.SeasonDTO;
import com.esaip.springboot.handball.entities.Season;
import com.esaip.springboot.handball.repositories.SeasonRepository;
import com.esaip.springboot.handball.services.SeasonService;
import com.esaip.springboot.handball.services.exceptions.SeasonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Season Service
 *
 * This implementation of the SeasonService interface communicates with
 * the database by using a Spring Data JPA repository.
 *
 * @author Guillaume MOREL-BAILLY
 */
@Service
public class SeasonServiceImpl implements SeasonService {

    @Autowired
    private SeasonRepository seasonRepository;

    /**
     * Finds all seasons
     *
     * @return A list of seasons
     */
    @Transactional(readOnly = true)
    public List<Season> getAll() {
        return seasonRepository.findAll();
    }

    /**
     * Finds season by ID
     *
     * @param id The ID of the wanted season
     * @return The found season. If no season is found, this method returns null.
     */
    @Transactional(readOnly = true)
    public Season get(Long id) {
        return seasonRepository.findOne(id);
    }

    /**
     * Creates a new season
     *
     * @param seasonForm The information of the created season
     * @return The created season
     */
    @Transactional(readOnly = false)
    public Season create(SeasonDTO seasonForm) {
        Season season = new Season(seasonForm.getName(), seasonForm.getStartAt(), seasonForm.getEndAt());

        return seasonRepository.save(season);
    }

    /**
     * Updates the information of a season
     *
     * @param seasonForm The information of the updated season
     * @return The updated season
     * @throws SeasonNotFoundException If no season is found with given ID
     */
    @Transactional(readOnly = false, rollbackFor = SeasonNotFoundException.class)
    public Season update(SeasonDTO seasonForm) throws SeasonNotFoundException {
        Season season = seasonRepository.findOne(seasonForm.getId());

        if (season == null) {
            throw new SeasonNotFoundException("No season found with id = " + seasonForm.getId());
        }

        season.setName(seasonForm.getName());
        season.setStartAt(seasonForm.getStartAt());
        season.setEndAt(seasonForm.getEndAt());

        return seasonRepository.save(season);
    }

    /**
     * Deletes a season
     *
     * @param id The ID of the deleted season
     * @throws SeasonNotFoundException If no season is found with the given ID
     */
    @Transactional(readOnly = false, rollbackFor = SeasonNotFoundException.class)
    public void delete(Long id) throws SeasonNotFoundException {
        Season season = seasonRepository.findOne(id);

        if (season == null) {
            throw new SeasonNotFoundException("No season found with id = " + id);
        }

        seasonRepository.delete(id);
    }

}
