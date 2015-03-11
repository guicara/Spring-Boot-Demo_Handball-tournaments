package com.esaip.springboot.handball.controllers.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Frontend dashboard controller
 *
 * @author Guillaume MOREL-BAILLY
 */
@Controller
public class DashboardController {

    @RequestMapping(value="/dashboard", method = RequestMethod.GET)
    public String index() {
        return "frontend/dashboard/index";
    }

}
