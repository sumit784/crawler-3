package com.qinyuan15.crawler.controller;

import com.qinyuan15.crawler.dao.CommodityFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.qinyuan15.crawler.controller.JsonControllerUtils.emptyMapJson;
import static com.qinyuan15.crawler.controller.JsonControllerUtils.toJson;

/**
 * Query detail information of certain commodity
 * Created by qinyuan on 14-12-27.
 */
@Controller
public class CommodityDetailController {
    @ResponseBody
    @RequestMapping("/commodityDetail")
    public String get(@RequestParam("pretty") String pretty, @RequestParam(value = "id", required = true) int id,
                      @RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime) {
        if (id <= 0) {
            return emptyMapJson;
        }

        CommodityFactory factory = new CommodityFactory();
        if (startTime != null) {
            factory.setStartTime(startTime);
        }
        if (endTime != null) {
            factory.setEndTime(endTime);
        }

        return toJson(factory.getInstance(id), pretty != null);
    }
}
