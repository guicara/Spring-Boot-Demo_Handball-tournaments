package com.esaip.springboot.handball.controllers.frontend;

import com.esaip.springboot.handball.entities.Team;
import com.esaip.springboot.handball.services.MatchService;
import com.esaip.springboot.handball.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Frontend dashboard controller
 *
 * @author Guillaume MOREL-BAILLY
 */
@Controller
public class DashboardController {

    @Autowired
    private MatchService matchService;

    @Autowired
    private TeamService teamService;

    /**
     * Dashboard
     */
    @RequestMapping(value="/dashboard", method = RequestMethod.GET)
    public String index() {
        return "frontend/dashboard/index";
    }

    /**
     * Ranking table
     */
    @RequestMapping(value="/ranking", method = RequestMethod.GET)
    public String ranking() {
        return "frontend/dashboard/ranking";
    }

    /**
     * Display a listing of the teams
     */
    @RequestMapping(value="/teams", method = RequestMethod.GET)
    public String teams(Model model) {
        model.addAttribute("teams", teamService.getAll());

        return "frontend/dashboard/teams";
    }

    @RequestMapping(value = "/teams/{teamId}", method = RequestMethod.GET)
    public String show(@PathVariable Long teamId, Model model) {
        Team team = teamService.get(teamId);
        if (team == null) {
            return "redirect:/teams";
        }

        model.addAttribute("team", team);

        return "frontend/dashboard/teams_view";
    }

    /**
     * Display a listing of the matchs
     */
    @RequestMapping(value="/matchs", method = RequestMethod.GET)
    public String matchs(Model model) {
        model.addAttribute("matchs", matchService.getAll());

        return "frontend/dashboard/matchs";
    }

}
