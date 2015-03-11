package com.esaip.springboot.handball.controllers.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * AdminController
 *
 * @author Guillaume MOREL-BAILLY
 */
@Controller
public class AdminController {

    @RequestMapping(value="/admin", method = RequestMethod.GET)
    public String index() {
        return "backend/home";
    }

}
