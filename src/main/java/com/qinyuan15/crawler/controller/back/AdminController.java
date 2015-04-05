package com.qinyuan15.crawler.controller.back;

import com.qinyuan15.crawler.controller.ImageController;
import com.qinyuan15.crawler.core.commodity.CommoditySimpleSnapshot;
import com.qinyuan15.crawler.core.commodity.CommoditySimpleSnapshotBuilder;
import com.qinyuan15.crawler.dao.AppConfig;
import com.qinyuan15.crawler.dao.Commodity;
import com.qinyuan15.crawler.dao.CommodityDao;
import com.qinyuan15.crawler.security.SecurityUtils;
import com.qinyuan15.crawler.ui.PaginationAnchor;
import com.qinyuan15.crawler.ui.PaginationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Admin page
 * Created by qinyuan on 15-2-19.
 */
@Controller
public class AdminController extends ImageController {
    private final static Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @RequestMapping("/admin")
    public String index(ModelMap model,
                        @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                        @RequestParam(value = "keyWord", required = false) String keyWord) {

        if (!isPositive(pageNumber)) {
            pageNumber = 1;
        }

        CommodityDao.Factory factory = CommodityDao.factory();

        // security
        if (!SecurityUtils.isSupperAdmin()) {
            Integer userId = SecurityUtils.getUserId();
            factory.setUserId(userId);
        }

        // key word
        if (StringUtils.hasText(keyWord)) {
            factory.setKeyWord(keyWord);
        }

        AppConfig appConfig = getAppConfig();
        Integer pageSize = appConfig.getAdminPaginationCommoditySize();
        if (!isPositive(pageSize)) {
            pageSize = Integer.MAX_VALUE;
        }
        Integer visibleButtonCount = appConfig.getAdminPaginationButtonSize();
        if (!isPositive(visibleButtonCount)) {
            visibleButtonCount = Integer.MAX_VALUE;
        }

        long commodityCount = factory.getCount();
        int pageCount = PaginationUtils.getPageCount(commodityCount, pageSize);
        if (pageNumber > pageCount) {
            pageNumber = pageCount;
        }

        String pageUrl = "admin";
        if (StringUtils.hasText(keyWord)) {
            try {
                pageUrl = pageUrl + "?keyWord=" + URLEncoder.encode(keyWord, "utf-8");
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("fail to encode keyWord '{}', info: '{}'", keyWord, e);
            }
        }

        List<PaginationAnchor> anchors = PaginationAnchor.create(
                pageUrl, pageCount, visibleButtonCount, pageNumber);
        model.addAttribute("paginationAnchors", anchors);
        model.addAttribute("commodityCount", commodityCount);
        model.addAttribute("keyWord", keyWord);

        List<Commodity> commodities = factory.getInstances((pageNumber - 1) * pageSize, pageSize);
        List<CommoditySimpleSnapshot> snapshots = new CommoditySimpleSnapshotBuilder().build(
                commodities, pictureUrlConverter);
        model.addAttribute("commodities", snapshots);
        setTitle("商品管理");

        return "admin";
    }
}
