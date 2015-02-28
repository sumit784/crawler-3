package com.qinyuan15.crawler.controller.json;

import com.qinyuan15.crawler.controller.BaseController;
import com.qinyuan15.crawler.dao.Commodity;
import com.qinyuan15.crawler.dao.CommodityDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Controller to validate commodity
 * Created by qinyuan on 15-2-28.
 */
@Controller
public class CommodityValidatorController extends BaseController {
    /**
     * Validating if there is showId conflict
     *
     * @param id     id of commodity to validate
     * @param showId showId of commodity to validate
     * @return return false if there is showId conflict, else return true
     */
    @ResponseBody
    @RequestMapping("/json/commodityShowIdValidate.json")
    public String index(@RequestParam(value = "id", required = false) Integer id,
                        @RequestParam(value = "showId", required = true) String showId) {
        List<Commodity> commodities = new CommodityDao().getInstancesByShowId(showId);
        if (isPositive(id)) {
            for (Commodity commodity : commodities) {
                if (!commodity.getId().equals(id)) {
                    return toJson(createSimpleMap(RESULT, false));
                }
            }
            return toJson(createSimpleMap(RESULT, true));
        } else {
            return toJson(createSimpleMap(RESULT, commodities.size() == 0));
        }
    }
}
