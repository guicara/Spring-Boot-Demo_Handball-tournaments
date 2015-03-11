package com.esaip.springboot.handball.controllers.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Frontend Home controller
 *
 * @author Guillaume MOREL-BAILLY
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String index() {
        return "frontend/pages/home";
    }

    @RequestMapping("/about")
    public String about() {
        return "frontend/pages/about";
    }

}
