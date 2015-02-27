package com.qinyuan15.crawler.controller.json;

import com.qinyuan15.crawler.controller.BaseController;
import com.qinyuan15.crawler.dao.Commodity;
import com.qinyuan15.crawler.dao.HibernateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Controller to delete Commodity
 * Created by qinyuan on 15-1-22.
 *//*
@Controller
public class CommodityDeleteController extends BaseController{
    @ResponseBody
    @RequestMapping("/deleteCommodity.json")
    public Map<String, Object> delete(@RequestParam(value = "id", required = true) Integer id) {
        try {
            HibernateUtil.delete(Commodity.class, id);
            return SUCCESS;
        } catch (Exception e) {
            return createFailResult(e);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCommodities.json", method = RequestMethod.POST)
    public Map<String, Object> delete(@RequestParam(value = "ids[]", required = true) List<Integer> ids) {
        try {
            for (Integer id : ids) {
                if (id != null) {
                    HibernateUtil.delete(Commodity.class, id);
                }
            }
            return SUCCESS;
        } catch (Exception e) {
            return createFailResult(e);
        }
    }
}*/
