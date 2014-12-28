package com.qinyuan15.crawler.controller;

import com.qinyuan15.crawler.dao.SimpleCommodityFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.qinyuan15.crawler.controller.JsonControllerUtils.toJson;

/**
 * Query commodities and their basic information
 * Created by qinyuan on 14-12-27.
 */
@Controller
public class CommodityBasicController {
    @ResponseBody
    @RequestMapping("/commodityBasic")
    public String get(@RequestParam("pretty") String pretty, @RequestParam("category") int categoryId,
                      @RequestParam("branch") int branchId, @RequestParam("keyWord") String keyWord) {
        SimpleCommodityFactory factory = new SimpleCommodityFactory();
        if (categoryId > 0) {
            factory.setCategoryId(categoryId);
        }
        if (branchId > 0) {
            factory.setBranchId(branchId);
        }
        if (keyWord != null) {
            factory.setKeyWord(keyWord);
        }
        return toJson(factory.getInstances(), pretty != null);
    }
}
