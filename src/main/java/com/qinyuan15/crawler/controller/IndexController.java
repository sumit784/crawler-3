package com.qinyuan15.crawler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.HashMap;
import java.util.Map;

/**
 * Index page, just for testing
 * Created by qinyuan on 14-12-27.
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String printHello(ModelMap model) {
        model.addAttribute("message", "It Work!");
        return "index";
    }

    @ResponseBody
    @RequestMapping("/index.json")
    public String indexJson() {
        return "{hello:\"world\"}";
    }
}
