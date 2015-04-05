package com.qinyuan15.crawler.controller.back;

import com.qinyuan15.crawler.controller.ImageController;
import com.qinyuan15.crawler.dao.CommodityCrawlLogDao;
import com.qinyuan15.crawler.ui.PaginationAttributeAdder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Page to edit branch
 * Created by qinyuan on 15-2-19.
 */
@Controller
public class AdminCrawlLogController extends ImageController {
    private final static int PAGE_SIZE = 50;
    private final static int VISIBLE_BUTTON_SIZE = 10;

    @RequestMapping("/admin-crawl-log")
    public String index(ModelMap model,
                        @RequestParam(value = "commodityId", required = false) Integer commodityId,
                        @RequestParam(value = "success", required = false) Integer success,
                        @RequestParam(value = "showId", required = false) String showId,
                        @RequestParam(value = "pageNumber", required = false) Integer pageNumber) {

        /*if (!isPositive(pageNumber)) {
            pageNumber = 1;
        }*/

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

        CommodityCrawlLogDao.Factory factory = CommodityCrawlLogDao.factory()
                .setCommodityId(commodityId).setSuccess(successValue).setCommodityShowId(showId);

        PaginationAttributeAdder attributeAdder = new PaginationAttributeAdder(factory, pageNumber)
                .addRequestParameter("commodityId", commodityId)
                .addRequestParameter("showId", showId)
                .addRequestParameter("success", success);
        attributeAdder.addAttributes("admin-crawl-log", "crawlLogs", model);

        /*long commodityCount = factory.getCount();
        int pageCount = PaginationUtils.getPageCount(commodityCount, PAGE_SIZE);
        if (pageNumber > pageCount) {
            pageNumber = pageCount;
        }

        String pageUrl = "admin-crawl-log?";
        if (commodityId != null) {
            pageUrl += "&commodityId=" + commodityId;
        }
        if (showId != null) {
            pageUrl += "&showId=" + showId;
        }
        if (success != null) {
            pageUrl += "&success=" + success;
        }

        List<PaginationAnchor> anchors = PaginationAnchor.create(
                pageUrl, pageCount, VISIBLE_BUTTON_SIZE, pageNumber);
        model.addAttribute("paginationAnchors", anchors);
        model.addAttribute("rowStartIndex", (pageNumber - 1) * PAGE_SIZE + 1);

        List<CommodityCrawlLog> crawlLogs = factory.getInstances((pageNumber - 1) * PAGE_SIZE, PAGE_SIZE);
        model.addAttribute("crawlLogs", crawlLogs);*/

        addCssAndJs("admin-normal-edit-page");
        setTitle("爬虫日志");
        return "admin-crawl-log";
    }
}
