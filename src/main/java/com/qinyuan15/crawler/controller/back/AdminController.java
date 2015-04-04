package com.qinyuan15.crawler.controller.back;

import com.qinyuan15.crawler.controller.ImageController;
import com.qinyuan15.crawler.core.commodity.CommoditySimpleSnapshot;
import com.qinyuan15.crawler.core.commodity.CommoditySimpleSnapshotBuilder;
import com.qinyuan15.crawler.dao.AppConfig;
import com.qinyuan15.crawler.dao.Commodity;
import com.qinyuan15.crawler.dao.CommodityDao;
import com.qinyuan15.crawler.security.SecurityUtils;
import com.qinyuan15.crawler.ui.PaginationAnchor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Admin page
 * Created by qinyuan on 15-2-19.
 */
@Controller
public class AdminController extends ImageController {

    @RequestMapping("/admin")
    public String index(ModelMap model,
                        @RequestParam(value = "pageNumber", required = false) Integer pageNumber) {

        if (!isPositive(pageNumber)) {
            pageNumber = 1;
        }

        CommodityDao.Factory factory = CommodityDao.factory();

        if (!SecurityUtils.isSupperAdmin()) {
            Integer userId = SecurityUtils.getUserId();
            factory.setUserId(userId);
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
        int pageCount = commodityCount == 0 ? 1 :
                (int) (Math.ceil((double) commodityCount / pageSize));

        List<PaginationAnchor> anchors = PaginationAnchor.create("admin", pageCount, visibleButtonCount, pageNumber);
        model.addAttribute("paginationAnchors", anchors);
        model.addAttribute("commodityCount", commodityCount);

        List<Commodity> commodities = factory.getInstances((pageNumber - 1) * pageSize, pageSize);
        List<CommoditySimpleSnapshot> snapshots = new CommoditySimpleSnapshotBuilder().build(
                commodities, pictureUrlConverter);
        model.addAttribute("commodities", snapshots);
        setTitle("商品管理");

        return "admin";
    }
}
