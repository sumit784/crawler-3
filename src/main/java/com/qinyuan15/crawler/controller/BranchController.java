package com.qinyuan15.crawler.controller;

import com.qinyuan15.crawler.dao.Branch;
import com.qinyuan15.crawler.dao.BranchFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.qinyuan15.crawler.controller.JsonControllerUtils.emptyMapJson;
import static com.qinyuan15.crawler.controller.JsonControllerUtils.toJson;

/**
 * Query branches
 * Created by qinyuan on 14-12-27.
 */
@Controller
public class BranchController {

    @ResponseBody
    @RequestMapping("/branch")
    public String get(@RequestParam("pretty") String pretty, @RequestParam("id") int id) {
        BranchFactory factory = new BranchFactory();
        if (id > 0) {
            Branch branch = factory.getInstance(id);
            if (branch == null) {
                return emptyMapJson;
            } else {
                return toJson(factory.getInstance(id), pretty != null);
            }
        } else {
            return toJson(factory.getInstances(), pretty != null);
        }
    }
}
