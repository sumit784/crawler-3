package com.qinyuan15.crawler.controller;

import com.qinyuan15.crawler.dao.Commodity;
import com.qinyuan15.crawler.dao.CommodityDao;
import com.qinyuan15.crawler.dao.CommodityFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static com.qinyuan15.crawler.controller.JsonControllerUtils.emptyMapJson;
import static com.qinyuan15.crawler.controller.JsonControllerUtils.toJson;

/**
 * Query detail information of certain commodity
 * Created by qinyuan on 14-12-27.
 */
@Controller
public class CommodityController {
    @ResponseBody
    @RequestMapping("/commodity.json")
    public String get(@RequestParam(value = "pretty", required = false) String pretty,
                      @RequestParam(value = "id", required = false) Integer id) {

        List<Commodity> commodities = CommodityDao.factory().setId(id).getInstances();

        return toJson(convert(commodities), pretty != null);
    }

    /**
     * Convert Commodity to CommodityJson
     *
     * @param commodities Commodity list to convert
     * @return A map containing CommodityJson
     */
    private Map<Integer, CommodityJson> convert(List<Commodity> commodities) {
        Map<Integer, CommodityJson> commodityJsonMap = new TreeMap<Integer, CommodityJson>();
        for (Commodity commodity : commodities) {
            Integer id = commodity.getId();
            CommodityJson commodityJson = new CommodityJson();
            commodityJson.name = commodity.getName();
            commodityJson.url = commodity.getUrl();
            commodityJsonMap.put(id, commodityJson);
        }
        return commodityJsonMap;
    }

    public static class CommodityJson {
        public String name;
        public String url;
    }
}
