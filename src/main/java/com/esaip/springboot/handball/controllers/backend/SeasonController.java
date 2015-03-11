package com.esaip.springboot.handball.controllers.backend;

import com.esaip.springboot.handball.dto.SeasonDTO;
import com.esaip.springboot.handball.entities.Season;
import com.esaip.springboot.handball.services.SeasonService;
import com.esaip.springboot.handball.services.exceptions.SeasonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Backend seasons controller
 *
 * @author Guillaume MOREL-BAILLY
 */
@Controller
public class SeasonController {

    @Autowired
    SeasonService seasonService;

    /**
     * CustomDateEditor for converting the user input String to date.
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true);
        binder.registerCustomEditor(Date.class, editor);
    }

    /**
     * Display a listing of the resource.
     * GET /admin/seasons
     */
    @RequestMapping(value = "/admin/seasons", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("seasons", seasonService.getAll());
        return "backend/seasons/index";
    }

    /**
     * Show the form for creating a new resource.
     * GET /admin/seasons/create
     */
    @RequestMapping(value = "/admin/seasons/create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("season", new SeasonDTO());
        return "backend/seasons/create";
    }

    /**
     * Store a newly created resource in storage.
     * POST /admin/seasons
     */
    @RequestMapping(value = "/admin/seasons", method = RequestMethod.POST)
    public String store(@Valid @ModelAttribute("season") SeasonDTO seasonForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "backend/seasons/create";
        }

        Season newSeason = seasonService.create(seasonForm);

        return "redirect:/admin/seasons";
    }

    /**
     * Display the specified resource.
     * GET /admin/seasons/{id}
     *
     * @deprecated Not used (see edit())
     */
    @RequestMapping(value = "/admin/seasons/{seasonId}", method = RequestMethod.GET)
    public String show(@PathVariable Long seasonId) {
        return null;
    }

    /**
     * Show the form for editing the specified resource.
     * GET /admin/seasons/{id}/edit
     */
    @RequestMapping(value = "/admin/seasons/{seasonId}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable Long seasonId, Model model) {
        Season season = seasonService.get(seasonId);
        if (season == null) {
            return "redirect:/admin/seasons";
        }

        model.addAttribute("season", season);

        return "backend/seasons/edit";
    }

    /**
     * Update the specified resource in storage.
     * PUT /admun/seasons/{id}
     */
    @RequestMapping(value = "/admin/seasons/{seasonId}", method = RequestMethod.PUT)
    public String update(@PathVariable Long seasonId, @Valid @ModelAttribute("season") SeasonDTO seasonForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "backend/seasons/edit";
        }

        try {
            Season season = seasonService.update(seasonForm);
        } catch (SeasonNotFoundException e) {
            bindingResult.reject("season.error.notfound");
            return "backend/seasons/edit";
        }

        return "redirect:/admin/seasons/" + seasonId + "/edit";
    }

    /**
     * Remove the specified resource from storage.
     * DELETE /admin/seasons/{id}
     */
    @RequestMapping(value = "/admin/seasons/{seasonId}", method = RequestMethod.DELETE)
    public String destroy(@PathVariable Long seasonId) {

        try {
            seasonService.delete(seasonId);
        } catch (SeasonNotFoundException e) {
            // TODO
        }

        return "redirect:/admin/seasons";
    }

}
