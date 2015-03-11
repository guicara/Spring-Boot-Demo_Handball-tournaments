package com.esaip.springboot.handball.controllers.frontend;

import com.esaip.springboot.handball.dto.RegistrationDTO;
import com.esaip.springboot.handball.entities.User;
import com.esaip.springboot.handball.services.SignService;
import com.esaip.springboot.handball.services.exceptions.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Frontend SignController
 *
 * @author Guillaume MOREL-BAILLY
 */
@Controller
public class SignController {

    @Autowired
    SignService signService;

    /**
     * Display sign in page
     * GET /login
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "frontend/auth/login";
    }

    /**
     * Display sign up page
     * GET /register
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("register", new User());
        return "frontend/auth/signup";
    }

    /**
     * Register new user
     * POST /register
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String store(@Valid @ModelAttribute("register") RegistrationDTO registrationForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "frontend/auth/signup";
        }

        try {
            User registered = signService.register(registrationForm);
        } catch (UserAlreadyExistsException e) {
            bindingResult.reject("user.error.exists");
            return "frontend/auth/signup";
        }

        return "redirect:/login";
    }

}
