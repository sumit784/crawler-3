package com.qinyuan15.crawler.controller.json;

import com.qinyuan15.crawler.controller.BaseController;
import com.qinyuan15.crawler.core.commodity.CommodityPictureUrlConverter;
import com.qinyuan15.crawler.core.image.ImageDownloader;
import com.qinyuan15.crawler.dao.CommodityPictureDao;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CommodityPictureController extends BaseController {
    @Autowired
    private ImageDownloader imageDownloader;

    @ResponseBody
    @RequestMapping("/commodityPicture.json")
    public String get(@RequestParam(value = "commodityId", required = true) Integer commodityId) {
        CommodityPictureUrlConverter urlConverter = new CommodityPictureUrlConverter(
                imageDownloader, request.getLocalAddr());
        CommodityPictureDao dao = new CommodityPictureDao();
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("pictures", urlConverter.pathsToUrls(dao.getInstances(commodityId)));
        jsonMap.put("detailPictures", urlConverter.pathsToUrls(dao.getDetailInstances(commodityId)));
        return toJson(jsonMap);
    }
}
