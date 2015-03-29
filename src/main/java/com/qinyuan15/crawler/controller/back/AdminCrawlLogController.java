package com.qinyuan15.crawler.controller.back;

import com.qinyuan15.crawler.controller.ImageController;
import com.qinyuan15.crawler.dao.CommodityCrawlLog;
import com.qinyuan15.crawler.dao.CommodityCrawlLogDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Page to edit branch
 * Created by qinyuan on 15-2-19.
 */
@Controller
public class AdminCrawlLogController extends ImageController {
    @RequestMapping("/admin-crawl-log")
    public String index(ModelMap model,
                        @RequestParam(value = "commodityId", required = false) Integer commodityId,
                        @RequestParam(value = "success", required = false) Integer success,
                        @RequestParam(value = "showId", required = false) String showId) {

        Boolean successValue;
        if (success == null) {
            successValue = null;
        } else if (success.equals(1)) {
            successValue = true;
        } else if (success.equals(2)) {
            successValue = false;
        } else {
            successValue = null;
        }

        List<CommodityCrawlLog> crawlLogs = CommodityCrawlLogDao.factory()
                .setCommodityId(commodityId).setSuccess(successValue).setCommodityShowId(showId)
                .getInstances();
        model.addAttribute("crawlLogs", crawlLogs);
        addCssAndJs("admin-normal-edit-page");
        setTitle("爬虫日志");
        return "admin-crawl-log";
    }
}
