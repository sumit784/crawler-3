package com.qinyuan15.crawler.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.qinyuan15.crawler.controller.utils.JspControllerUtils.setTitle;

/**
 * BranchController
 * Created by qinyuan on 15-2-14.
 */
@Controller
public class BranchController {

    @RequestMapping("/branch")
    public String index(ModelMap model) {
        setTitle(model, "品牌大全");
        return "branch";
    }
}
