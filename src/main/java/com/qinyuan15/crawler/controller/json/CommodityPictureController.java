package com.qinyuan15.crawler.controller.json;

import com.qinyuan15.crawler.controller.ImageController;
import com.qinyuan15.crawler.dao.CommodityPictureDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Query detail information of certain commodity
 * Created by qinyuan on 14-12-27.
 */
@Controller
public class CommodityPictureController extends ImageController {
    @ResponseBody
    @RequestMapping("/commodityPicture.json")
    public String get(@RequestParam(value = "commodityId", required = true) Integer commodityId) {
        CommodityPictureDao dao = new CommodityPictureDao();
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("pictures", parseCommodityPictureSmallUrls(dao.getInstances(commodityId)));
        jsonMap.put("detailPictures", parseCommodityPictureSmallUrls(dao.getDetailInstances(commodityId)));
        return toJson(jsonMap);
    }
}
