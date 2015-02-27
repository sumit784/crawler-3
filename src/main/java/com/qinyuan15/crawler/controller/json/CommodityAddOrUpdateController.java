package com.qinyuan15.crawler.controller.json;

import com.qinyuan15.crawler.controller.BaseController;
import com.qinyuan15.crawler.dao.Commodity;
import com.qinyuan15.crawler.dao.HibernateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Add or update Commodity
 * Created by qinyuan on 15-1-22.
 *//*
@Controller
public class CommodityAddOrUpdateController extends BaseController{
    @ResponseBody
    @RequestMapping(value = "/addOrUpdateCommodity.json", method = RequestMethod.POST)
    public Map<String, Object> addOrUpdate(@RequestParam(value = "name", required = true) String name,
                                           @RequestParam(value = "url", required = true) String url,
                                           @RequestParam(value = "price", required = true) Double price,
                                           @RequestParam(value = "onShelfTime", required = false) String onShelfTime,
                                           @RequestParam(value = "id", required = false) Integer id) {

        try {
            addOrUpdateCommodity(name, url, price, onShelfTime, id);
            return SUCCESS;
        } catch (Exception e) {
            return createFailResult(e);
        }
    }


    @ResponseBody
    @RequestMapping(value = "/addOrUpdateCommodities.json", method = RequestMethod.POST)
    public Map<String, Object> addOrUpdate(@RequestBody List<CommodityParam> param) {
        // validate post parameters
        for (CommodityParam commodityParam : param) {
            if (commodityParam.name == null) {
                return createFailResult("parameter name is needed");
            }
            if (commodityParam.url == null) {
                return createFailResult("parameter url is needed");
            }
            if (commodityParam.price == null) {
                return createFailResult("parameter price is needed");
            }
        }

        try {
            for (CommodityParam commodityParam : param) {
                addOrUpdateCommodity(
                        commodityParam.name,
                        commodityParam.url,
                        commodityParam.price,
                        commodityParam.onShelfTime,
                        commodityParam.id
                );
            }
            return SUCCESS;
        } catch (Exception e) {
            return createFailResult(e);
        }
    }

    private void addOrUpdateCommodity(String name, String url, Double price, String onShelfTime, Integer id) {
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
    }

    public static class CommodityParam {
        public String name;
        public String url;
        public Double price;
        public String onShelfTime;
        public Integer id;
    }
}*/
