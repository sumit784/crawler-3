package com.qinyuan15.crawler.controller;

import com.qinyuan15.crawler.dao.Commodity;
import com.qinyuan15.crawler.dao.CommodityDao;
import com.qinyuan15.crawler.dao.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static com.qinyuan15.crawler.controller.JsonControllerUtils.createResultMap;
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

    @ResponseBody
    @RequestMapping(value = "/addOrUpdateCommodity.json", method = RequestMethod.POST)
    public Map<String, Object> addOrUpdate(@RequestParam(value = "name", required = true) String name,
                                           @RequestParam(value = "url", required = true) String url,
                                           @RequestParam(value = "price", required = true) Double price,
                                           @RequestParam(value = "onShelfTime", required = false) String onShelfTime,
                                           @RequestParam(value = "id", required = false) Integer id) {

        try {
            Commodity commodity = new Commodity();
            commodity.setName(name);
            commodity.setUrl(url);
            commodity.setPrice(price);
            commodity.setOnShelfTime(onShelfTime);
            if (id != null && id > 0) {
                commodity.setId(id);
                HibernateUtil.saveOrUpdate(commodity);
            } else {
                HibernateUtil.save(commodity);
            }
            return createResultMap(true, null);
        } catch (Exception e) {
            return createResultMap(false, e.toString());
        }
    }

    @ResponseBody
    @RequestMapping("/deleteCommodity.json")
    public Map<String, Object> addOrUpdate(@RequestParam(value = "id", required = true) Integer id) {
        try {
            Session session = HibernateUtil.getSession();
            Commodity commodity = (Commodity) session.get(Commodity.class, id);
            if (commodity != null) {
                session.delete(commodity);
            }
            HibernateUtil.commit(session);
            return createResultMap(true, null);
        } catch (Exception e) {
            return createResultMap(false, e.toString());
        }
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
            commodityJson.pictures = new ArrayList<String>();
            commodityJsonMap.put(id, commodityJson);
        }
        return commodityJsonMap;
    }

    public static class CommodityJson {
        public String name;
        public String url;
        public List<String> pictures;
    }
}
