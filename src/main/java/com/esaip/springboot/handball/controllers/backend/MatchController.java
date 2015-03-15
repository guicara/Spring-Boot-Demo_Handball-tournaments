package com.esaip.springboot.handball.controllers.backend;

import com.esaip.springboot.handball.dto.MatchDTO;
import com.esaip.springboot.handball.entities.Match;
import com.esaip.springboot.handball.entities.Season;
import com.esaip.springboot.handball.services.MatchService;
import com.esaip.springboot.handball.services.SeasonService;
import com.esaip.springboot.handball.services.TeamService;
import com.esaip.springboot.handball.services.exceptions.MatchNotFoundException;
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
 * Backend matchs controller
 *
 * @author Guillaume MOREL-BAILLY
 */
@Controller
public class MatchController {

    @Autowired
    private MatchService matchService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private SeasonService seasonService;

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
     * GET /admin/matchs
     */
    @RequestMapping(value = "/admin/matchs", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("matchs", matchService.getAll());
        return "backend/matchs/index";
    }

    /**
     * Show the form for creating a new resource.
     * GET /admin/matchs/create
     */
    @RequestMapping(value = "/admin/matchs/create", method = RequestMethod.GET)
    public String create(Model model) {
        // Populates the teams and seasons dropdown
        model.addAttribute("teams", teamService.getAll());
        model.addAttribute("seasons", seasonService.getAll());

        // Populates the match form
        model.addAttribute("match", new MatchDTO());

        return "backend/matchs/create";
    }

    /**
     * Store a newly created resource in storage.
     * POST /admin/matchs
     */
    @RequestMapping(value = "/admin/matchs", method = RequestMethod.POST)
    public String store(@Valid @ModelAttribute("match") MatchDTO matchForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "backend/matchs/create";
        }

        Match newMatch = matchService.create(matchForm);

        return "redirect:/admin/matchs";
    }

    /**
     * Display the specified resource.
     * GET /admin/matchs/{id}
     *
     * @deprecated Not used (see edit())
     */
    @RequestMapping(value = "/admin/matchs/{matchId}", method = RequestMethod.GET)
    public String show(@PathVariable Long matchId) {
        return null;
    }

    /**
     * Show the form for editing the specified resource.
     * GET /admin/matchs/{id}/edit
     */
    @RequestMapping(value = "/admin/matchs/{matchId}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable Long matchId, Model model) {
        Match match = matchService.get(matchId);
        if (match == null) {
            return "redirect:/admin/matchs";
        }

        // Populates the teams and seasons dropdown
        model.addAttribute("teams", teamService.getAll());
        model.addAttribute("seasons", seasonService.getAll());

        // Populates the match form (with match comments)
        model.addAttribute("match", match);

        return "backend/matchs/edit";
    }

    /**
     * Update the specified resource in storage.
     * PUT /admun/matchs/{id}
     */
    @RequestMapping(value = "/admin/matchs/{matchId}", method = RequestMethod.PUT)
    public String update(@PathVariable Long matchId, @Valid @ModelAttribute("match") MatchDTO matchForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "backend/matchs/edit";
        }

        try {
            Match match = matchService.update(matchForm);
        } catch (MatchNotFoundException e) {
            bindingResult.reject("match.error.notfound");
            return "backend/matchs/edit";
        }

        return "redirect:/admin/matchs/" + matchId + "/edit";
    }

    /**
     * Remove the specified resource from storage.
     * DELETE /admin/matchs/{id}
     */
    @RequestMapping(value = "/admin/matchs/{matchId}", method = RequestMethod.DELETE)
    public String destroy(@PathVariable Long matchId) {

        try {
            matchService.delete(matchId);
        } catch (MatchNotFoundException e) {
            // TODO
        }

        return "redirect:/admin/matchs";
    }

}
