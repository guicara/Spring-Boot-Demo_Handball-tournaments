package com.esaip.springboot.handball.controllers.backend;

import com.esaip.springboot.handball.dto.UserCreateDTO;
import com.esaip.springboot.handball.dto.UserEditDTO;
import com.esaip.springboot.handball.entities.User;
import com.esaip.springboot.handball.services.UserService;
import com.esaip.springboot.handball.services.exceptions.UserAlreadyExistsException;
import com.esaip.springboot.handball.services.exceptions.UserNotFoundException;
import com.google.common.base.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Backend users controller
 *
 * @author Guillaume MOREL-BAILLY
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    /**
     * Display a listing of the resource.
     * GET /admin/users
     */
    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("users", userService.getAll());
        return "backend/users/index";
    }

    /**
     * Show the form for creating a new resource.
     * GET /admin/users/create
     */
    @RequestMapping(value = "/admin/users/create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("user", new UserCreateDTO());
        return "backend/users/create";
    }

    /**
     * Store a newly created resource in storage.
     * POST /admin/users
     */
    @RequestMapping(value = "/admin/users", method = RequestMethod.POST)
    public String store(@Valid @ModelAttribute("user") UserCreateDTO userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "backend/users/create";
        }

        try {
            User newUser = userService.create(userForm);
        } catch (UserAlreadyExistsException e) {
            bindingResult.reject("user.error.exists");
            return "backend/users/create";
        }

        return "redirect:/admin/users";
    }

    /**
     * Display the specified resource.
     * GET /admin/users/{id}
     *
     * @deprecated Not used (see edit())
     */
    @RequestMapping(value = "/admin/users/{userId}", method = RequestMethod.GET)
    public String show(@PathVariable Long userId) {
        return null;
    }

    /**
     * Show the form for editing the specified resource.
     * GET /admin/users/{id}/edit
     */
    @RequestMapping(value = "/admin/users/{userId}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable Long userId, Model model) {
        User user = userService.get(userId);
        if (user == null) {
            return "redirect:/admin/users";
        }

        model.addAttribute("user", user);

        return "backend/users/edit";
    }

    /**
     * Update the specified resource in storage.
     * PUT /admun/users/{id}
     */
    @RequestMapping(value = "/admin/users/{userId}", method = RequestMethod.PUT)
    public String update(@PathVariable Long userId, @Valid @ModelAttribute("user") UserEditDTO userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "backend/users/edit";
        }

        try {
            User user = userService.update(userForm);
        } catch (UserNotFoundException e) {
            bindingResult.reject("user.error.notfound");
            return "backend/users/edit";
        }

        return "redirect:/admin/users/" + userId + "/edit";
    }

    /**
     * Remove the specified resource from storage.
     * DELETE /admin/users/{id}
     */
    @RequestMapping(value = "/admin/users/{userId}", method = RequestMethod.DELETE)
    public String destroy(@PathVariable Long userId) {

        try {
            userService.delete(userId);
        } catch (UserNotFoundException e) {
            // TODO
        }

        return "redirect:/admin/users";
    }

}
