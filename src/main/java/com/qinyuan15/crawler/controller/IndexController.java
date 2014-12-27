package com.qinyuan15.crawler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @RequestMapping("/index")
    public String printHello(ModelMap model) {
        model.addAttribute("message", "It Work!");
        return "index";
    }
}
