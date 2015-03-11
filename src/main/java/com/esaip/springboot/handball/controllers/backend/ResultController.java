package com.esaip.springboot.handball.controllers.backend;

import com.esaip.springboot.handball.dto.ResultDTO;
import com.esaip.springboot.handball.entities.Result;
import com.esaip.springboot.handball.services.ResultService;
import com.esaip.springboot.handball.services.exceptions.ResultNotFoundException;
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
 * Backend results controller
 *
 * @author Guillaume MOREL-BAILLY
 */
@Controller
public class ResultController {

    @Autowired
    ResultService resultService;

    /**
     * Display a listing of the resource.
     * GET /admin/results
     */
    @RequestMapping(value = "/admin/results", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("results", resultService.getAll());
        return "backend/results/index";
    }

    /**
     * Show the form for creating a new resource.
     * GET /admin/results/create
     */
    @RequestMapping(value = "/admin/results/create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("result", new ResultDTO());
        return "backend/results/create";
    }

    /**
     * Store a newly created resource in storage.
     * POST /admin/results
     */
    @RequestMapping(value = "/admin/results", method = RequestMethod.POST)
    public String store(@Valid @ModelAttribute("result") ResultDTO resultForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "backend/results/create";
        }

        Result newResult = resultService.create(resultForm);

        return "redirect:/admin/results";
    }

    /**
     * Display the specified resource.
     * GET /admin/results/{id}
     *
     * @deprecated Not used (see edit())
     */
    @RequestMapping(value = "/admin/results/{resultId}", method = RequestMethod.GET)
    public String show(@PathVariable Long resultId) {
        return null;
    }

    /**
     * Show the form for editing the specified resource.
     * GET /admin/results/{id}/edit
     */
    @RequestMapping(value = "/admin/results/{resultId}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable Long resultId, Model model) {
        Result result = resultService.get(resultId);
        if (result == null) {
            return "redirect:/admin/results";
        }

        model.addAttribute("result", result);

        return "backend/results/edit";
    }

    /**
     * Update the specified resource in storage.
     * PUT /admun/results/{id}
     */
    @RequestMapping(value = "/admin/results/{resultId}", method = RequestMethod.PUT)
    public String update(@PathVariable Long resultId, @Valid @ModelAttribute("result") ResultDTO resultForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "backend/results/edit";
        }

        try {
            Result result = resultService.update(resultForm);
        } catch (ResultNotFoundException e) {
            bindingResult.reject("result.error.notfound");
            return "backend/results/edit";
        }

        return "redirect:/admin/results/" + resultId + "/edit";
    }

    /**
     * Remove the specified resource from storage.
     * DELETE /admin/results/{id}
     */
    @RequestMapping(value = "/admin/results/{resultId}", method = RequestMethod.DELETE)
    public String destroy(@PathVariable Long resultId) {

        try {
            resultService.delete(resultId);
        } catch (ResultNotFoundException e) {
            // TODO
        }

        return "redirect:/admin/results";
    }

}
