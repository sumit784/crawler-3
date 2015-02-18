package com.qinyuan15.crawler.controller.json;

import com.qinyuan15.crawler.core.DateUtils;
import com.qinyuan15.crawler.dao.Commodity;
import com.qinyuan15.crawler.dao.CommodityDao;
import com.qinyuan15.crawler.dao.CommodityPriceDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.TreeMap;

import static com.qinyuan15.crawler.controller.utils.JsonControllerUtils.toJson;

/**
 * Query price information
 * Created by qinyuan on 15-1-22.
 */
@Controller
public class CommodityPriceController {
    @ResponseBody
    @RequestMapping("/commodityLowPrice.json")
    public String getLow(@RequestParam(value = "pretty", required = false) String pretty,
                         @RequestParam(value = "id", required = false) Integer id,
                         @RequestParam(value = "startTime", required = false) String startTime,
                         @RequestParam(value = "endTime", required = false) String endTime) {

        if (startTime == null) {
            startTime = DateUtils.threeMonthAgo().toString();
        }
        if (endTime == null) {
            endTime = DateUtils.now().toString();
        }

        Map<Integer, Double> commodityPriceJsonMap = new TreeMap<Integer, Double>();
        for (Commodity commodity : CommodityDao.factory().setId(id).getInstances()) {
            Integer commodityId = commodity.getId();
            commodityPriceJsonMap.put(commodityId, CommodityPriceDao.range(commodityId)
                    .setStartTime(startTime).setEndTime(endTime).getMin());
        }
        return toJson(commodityPriceJsonMap, pretty != null);
    }

    @ResponseBody
    @RequestMapping("/commodityHighPrice.json")
    public String getHigh(@RequestParam(value = "pretty", required = false) String pretty,
                          @RequestParam(value = "id", required = false) Integer id,
                          @RequestParam(value = "startTime", required = false) String startTime,
                          @RequestParam(value = "endTime", required = false) String endTime) {
        Map<Integer, Double> commodityPriceJsonMap = new TreeMap<Integer, Double>();
        for (Commodity commodity : CommodityDao.factory().setId(id).getInstances()) {
            Integer commodityId = commodity.getId();
            commodityPriceJsonMap.put(commodityId, CommodityPriceDao.range(commodityId)
                    .setStartTime(startTime).setEndTime(endTime).getMax());
        }
        return toJson(commodityPriceJsonMap, pretty != null);
    }
}

