package com.qinyuan15.crawler.controller.json;

import com.qinyuan15.crawler.controller.BaseController;
import com.qinyuan15.crawler.core.html.ComposableCommodityPageParser;
import com.qinyuan15.crawler.core.http.HttpClientPool;
import com.qinyuan15.crawler.core.http.HttpClientWrapper;
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

    @Autowired
    private ComposableCommodityPageParser pageParser;

    //@Autowired
    //private HttpClientPool httpClientPool;

    @ResponseBody
    @RequestMapping("/commodity-crawler.json")
    public String index(@RequestParam(value = "url", required = true) String url) {
        HttpClientWrapper client = new HttpClientWrapper();
        pageParser.setWebUrl(url);
        pageParser.setHTML(client.getContent(url));

        CommodityJson json = new CommodityJson();
        json.name = pageParser.getName();
        json.buyUrl = url.replace(".html", "").replaceFirst("http://s.etao.com/detail/",
                "http://detail.tmall.com/item.htm?id=");
        json.imageUrls = pageParser.getImageUrls();
        json.detailImageUrls = pageParser.getDetailImagesUrls();
        return toJson(json);
    }

    static class CommodityJson {
        String name;
        String buyUrl;
        List<String> imageUrls;
        List<String> detailImageUrls;
    }
}
