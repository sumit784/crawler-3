package com.qinyuan15.crawler.controller.json;

import com.qinyuan15.crawler.controller.BaseController;
import com.qinyuan15.crawler.dao.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller to validate commodity
 * Created by qinyuan on 15-2-28.
 */
@Controller
public class CommodityValidatorController extends BaseController {
    @ResponseBody
    @RequestMapping("/json/commodityShowIdValidate.json")
    public String index(@RequestParam(value = "id", required = false) Integer id,
                        @RequestParam(value = "showId", required = true) String showId) {
        List<Commodity> commodities = new CommodityDao().getInstancesByShowId(showId);
        if (isPositive(id)) {

        } else {

        }
        /*
        createSimpleMap("result",
                CommodityDao.factory().setShowId(showId).setId(id).getInstances().size()>0);
        CategoryDao dao = new CategoryDao();
        if (isPositive(parentId)) {
            return toJson(dao.getSubInstances(parentId));
        } else {
            return toJson(dao.getRootInstances());
        }
        */
        //return null;
    }
}
