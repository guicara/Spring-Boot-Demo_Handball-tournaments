package com.esaip.springboot.handball.controllers.backend;

import com.esaip.springboot.handball.dto.TeamDTO;
import com.esaip.springboot.handball.entities.Team;
import com.esaip.springboot.handball.services.TeamService;
import com.esaip.springboot.handball.services.exceptions.TeamAlreadyExistsException;
import com.esaip.springboot.handball.services.exceptions.TeamNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Backend teams controller
 *
 * @author Guillaume MOREL-BAILLY
 */
@Controller
public class TeamController {

    @Autowired
    private TeamService teamService;

    /**
     * Display a listing of the resource.
     * GET /admin/teams
     */
    @RequestMapping(value = "/admin/teams", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("teams", teamService.getAll());
        return "backend/teams/index";
    }

    /**
     * Show the form for creating a new resource.
     * GET /admin/teams/create
     */
    @RequestMapping(value = "/admin/teams/create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("team", new TeamDTO());
        return "backend/teams/create";
    }

    /**
     * Store a newly created resource in storage.
     * POST /admin/teams
     */
    @RequestMapping(value = "/admin/teams", method = RequestMethod.POST)
    public String store(@Valid @ModelAttribute("team") TeamDTO teamForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "backend/teams/create";
        }

        try {
            Team newTeam = teamService.create(teamForm);
        } catch (TeamAlreadyExistsException e) {
            bindingResult.reject("team.error.exists");
            return "backend/teams/create";
        }

        return "redirect:/admin/teams";
    }

    /**
     * Display the specified resource.
     * GET /admin/teams/{id}
     *
     * @deprecated Not used (see edit())
     */
    @RequestMapping(value = "/admin/teams/{teamId}", method = RequestMethod.GET)
    public String show(@PathVariable Long teamId) {
        return null;
    }

    /**
     * Show the form for editing the specified resource.
     * GET /admin/teams/{id}/edit
     */
    @RequestMapping(value = "/admin/teams/{teamId}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable Long teamId, Model model) {
        Team team = teamService.get(teamId);
        if (team == null) {
            return "redirect:/admin/teams";
        }

        model.addAttribute("team", team);

        return "backend/teams/edit";
    }

    /**
     * Update the specified resource in storage.
     * PUT /admun/teams/{id}
     */
    @RequestMapping(value = "/admin/teams/{teamId}", method = RequestMethod.PUT)
    public String update(@PathVariable Long teamId, @Valid @ModelAttribute("team") TeamDTO teamForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "backend/teams/edit";
        }

        try {
            Team team = teamService.update(teamForm);
        } catch (TeamNotFoundException e) {
            bindingResult.reject("team.error.notfound");
            return "backend/teams/edit";
        }

        return "redirect:/admin/teams/" + teamId + "/edit";
    }

    /**
     * Remove the specified resource from storage.
     * DELETE /admin/teams/{id}
     */
    @RequestMapping(value = "/admin/teams/{teamId}", method = RequestMethod.DELETE)
    public String destroy(@PathVariable Long teamId) {

        try {
            teamService.delete(teamId);
        } catch (TeamNotFoundException e) {
            // TODO
        }

        return "redirect:/admin/teams";
    }

}
