package com.qinyuan15.crawler.controller.json;

import com.qinyuan15.crawler.controller.BaseController;
import com.qinyuan15.crawler.core.html.ComposableCommodityPageParser;
import com.qinyuan15.crawler.core.http.HttpClientWrapper;
import com.qinyuan15.crawler.dao.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Real time crawler about commodity
 * Created by qinyuan on 14-12-27.
 */
@Controller
public class CommodityCrawlerController extends BaseController {
    private final static Logger LOGGER = LoggerFactory.getLogger(CommodityCrawlerController.class);

    @Autowired
    private ComposableCommodityPageParser pageParser;

    @ResponseBody
    @RequestMapping("/commodity-crawler.json")
    public String index(@RequestParam(value = "url", required = true) String url) {
        try {
            HttpClientWrapper client = new HttpClientWrapper();
            pageParser.setWebUrl(url);
            pageParser.setHTML(client.getContent(url));

            if (pageParser.isExpire()) {
                return getFailResultString();
            }

            CommodityJson json = new CommodityJson();
            json.name = pageParser.getName();
            // TODO method to calculate buy url should be improved
            json.buyUrl = url.replace(".html", "").replace("http://s.etao.com/detail/",
                    "http://detail.tmall.com/item.htm?id=");

            json.imageUrls = pageParser.getImageUrls();
            json.detailImageUrls = pageParser.getDetailImagesUrls();
            json.success = true;

            // adjust image picture url and image detail picture url
            AppConfig appConfig = getAppConfig();
            Integer maxCommodityPictureSize = appConfig.getMaxCommodityPictureSize();
            if (isPositive(maxCommodityPictureSize) && maxCommodityPictureSize < json.imageUrls.size()) {
                json.imageUrls = json.imageUrls.subList(0, maxCommodityPictureSize);
            }
            Integer maxCommodityDetailPictureSize = appConfig.getMaxCommodityDetailPictureSize();
            if (isPositive(maxCommodityDetailPictureSize) && maxCommodityDetailPictureSize < json.detailImageUrls.size()) {
                json.detailImageUrls = json.detailImageUrls.subList(0, maxCommodityDetailPictureSize);
            }

            return toJson(json);
        } catch (Exception e) {
            LOGGER.error("Error in parsing {}, info: {}", url, e);
            return getFailResultString();
        }
    }

    private String getFailResultString() {
        return toJson(createFailResult("商品链接无效！！！"));
    }

    static class CommodityJson {
        String name;
        String buyUrl;
        boolean success;
        List<String> imageUrls;
        List<String> detailImageUrls;
    }
}
